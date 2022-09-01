package cn.edu.hbpu.myblog.service.impl;

import cn.edu.hbpu.myblog.mapper.UserMapper;
import cn.edu.hbpu.myblog.model.User;
import cn.edu.hbpu.myblog.service.IUserService;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.setting.dialect.Props;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.core.lang.UUID;
import org.springframework.web.multipart.MultipartFile;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;


    public int change(User u) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", u.getUid());
        userMapper.update(u, queryWrapper);
        return 1;
    }


    public User getUserByMyuid(int uid) {
        return userMapper.getUserByMyuid(uid);
    }


    public User checkUser(User u) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", u.getUsername());
        queryWrapper.eq("password", u.getPassword());
        queryWrapper.eq("type", 1);
        return userMapper.selectOne(queryWrapper);
    }

    public User checkUser01(User u) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", u.getUsername());
        queryWrapper.eq("password", u.getPassword());
        queryWrapper.eq("type", 0);
        return userMapper.selectOne(queryWrapper);
    }


    public User getUserByUid(int uid,int getuid) {
        return userMapper.getUserByUid(uid,getuid);
    }


    public User visitor() {
        User user = new User();
        user.setImage("default.png");
        user.setNickname("游客");
        user.setUsername("user_"+UUID.randomUUID().toString(true));
        user.setPassword("pass_"+UUID.randomUUID().toString(true));
        user.setRegtime(LocalDateTime.now());
        user.setStatus(1);
        user.setType(0);      //未登录身份
        userMapper.insert(user);
        User reuser = checkUser01(user);
        return reuser;
    }


    public int registUser(User u) throws IllegalStateException, IOException {
        MultipartFile file = u.getImgfile();
        //判断文件是否为空,空则返回失败信息
        if(file.isEmpty()) {
            return 0;
        }
        //获取文件上传路径
        Props prop = Props.getProp("db.properties");
        String path =prop.getStr("uploadPath");
        //获取原文件名
        String fileName = file.getOriginalFilename();
        String newName=UUID.randomUUID().toString(true)+"."+ FileNameUtil.extName(fileName);
        //创建文件实例
        File filePath = new File(path,newName);
        //如果文件目录不存在，创建目录
        if(!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" +filePath);
        }
        //写入文件
        file.transferTo(filePath);
        u.setImage(newName);
        u.setRegtime(LocalDateTime.now());
        u.setNickname("blog_"+u.getUid()*9);
        u.setType(1);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", u.getUid());
        userMapper.update(u, queryWrapper);
        return 1;
    }


    public String checkUsername(User u) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", u.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if(user==null) {
            return "notExist";
        }else {
            return "exist";
        }
    }

}
