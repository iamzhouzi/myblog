package cn.edu.hbpu.myblog.controller;

import cn.edu.hbpu.myblog.mapper.UserMapper;
import cn.edu.hbpu.myblog.model.Care;
import cn.edu.hbpu.myblog.model.QueryVo;
import cn.edu.hbpu.myblog.model.User;
import cn.edu.hbpu.myblog.service.ICareService;
import cn.edu.hbpu.myblog.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ICareService careService;

    //@Autowired
    //RocketMQTemplate rocketMQTemplate;
    @Autowired
    UserMapper userMapper;


    @ResponseBody
    @GetMapping("/getPage")
    public IPage<User> page(Page<User> page) {
        return userService.page(page);
    }

    @ResponseBody
    @GetMapping("/disable")
    public int disabled(Integer uid, Integer type) {
        if(type == 1){
            type=0;
        } else {
            type=1;
        }
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("uid", uid);
        updateWrapper.set("type", type);
        userService.update(updateWrapper);
        return 1;
    }


    /**
     * 查询关注列表
     *
     * @param queryVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCareListByUid")
    public PageInfo<User> getCareListByUid(@RequestBody QueryVo queryVo) {
        return careService.getCareListByUid(queryVo.getPageNum(), 10, queryVo.getUid());
    }

    /**
     * 查询粉丝列表
     *
     * @param queryVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFansListByTouid")
    public PageInfo<User> getFansListByTouid(@RequestBody QueryVo queryVo) {
        return careService.getFansListByTouid(queryVo.getPageNum(), 10, queryVo.getTouid());
    }

    /**
     * 添加关注
     *
     * @param care
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCare")
    public int addCare(@RequestBody Care care) {
        return careService.addCare(care.getUid(), care.getTouid());
    }

    /**
     * 取消关注
     *
     * @param care
     * @return
     */
    @ResponseBody
    @RequestMapping("/delCare")
    public int delCare(@RequestBody Care care) {
        return careService.delCare(care.getUid(), care.getTouid());
    }

    /**
     * 用户登录
     *
     * @param u
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public User login(@RequestBody User u) {
//        this.rocketMQTemplate.convertAndSend("login-log", u);
        return userService.checkUser(u);
    }

    /**
     * 用户详情页
     *
     * @param queryVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserByUid")
    public User getUserByUid(@RequestBody QueryVo queryVo) {
        return userService.getUserByUid(queryVo.getUid(), queryVo.getGetuid());
    }

    /**
     * 我的个人中心
     *
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserByMyuid")
    public User getUserByMyuid(int uid) {
        return userService.getUserByMyuid(uid);
    }

    /**
     * 创建游客用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/visitor")
    public User visitor() {
        return userService.visitor();
    }

    /**
     * 游客注册为正式用户
     *
     * @param u
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/regist")
    public int regist(User u) throws IOException {
        return userService.registUser(u);
    }

    /**
     * 查询是否同名
     *
     * @param u
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUsername")
    public String checkUsername(@RequestBody User u) {
        return userService.checkUsername(u);
    }

    /**
     * 修改普通信息
     *
     * @param u
     * @return
     */
    @ResponseBody
    @RequestMapping("/change")
    public int change(@RequestBody User u) {
        return userService.change(u);
    }

    /**
     * 修改密码
     *
     * @param u
     * @return
     */
    @ResponseBody
    @RequestMapping("/changepass")
    public int changepass(@RequestBody User u) {
        return userService.change(u);
    }
}
