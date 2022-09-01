package cn.edu.hbpu.myblog.service;

import cn.edu.hbpu.myblog.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

public interface IUserService extends IService<User> {
    int change(User u);

    User checkUser(User u);
    String checkUsername(User u);

    User getUserByUid(int uid,int getuid);
    User visitor();
    int registUser(User u) throws IOException;

    User getUserByMyuid(int uid);



}
