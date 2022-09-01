package cn.edu.hbpu.myblog.service.impl;

import cn.edu.hbpu.myblog.mapper.CareMapper;
import cn.edu.hbpu.myblog.model.Care;
import cn.edu.hbpu.myblog.model.User;
import cn.edu.hbpu.myblog.service.ICareService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CareService implements ICareService {
    @Autowired
    private CareMapper careMapper;


    public int addCare(int uid,int touid){
        Care care =new Care();
        care.setUid(uid);
        care.setTouid(touid);
        care.setCreatetime(LocalDateTime.now());
        careMapper.insert(care);
        return 1;
    }


    public int delCare(int uid,int touid){
        QueryWrapper<Care> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        queryWrapper.eq("touid",touid);
        careMapper.delete(queryWrapper);
        return 1;
    }


    public PageInfo<User> getCareListByUid(int pageNum, int pageSize, int uid) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = careMapper.getCareListByUid(uid);
        PageInfo<User> pageInfo=new PageInfo<User>(list);
        return pageInfo;
    }


    public PageInfo<User> getFansListByTouid(int pageNum, int pageSize, int touid) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = careMapper.getFansListByTouid(touid);
        PageInfo<User> pageInfo=new PageInfo<User>(list);
        return pageInfo;
    }


}
