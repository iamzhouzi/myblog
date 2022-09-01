package cn.edu.hbpu.myblog.service;

import cn.edu.hbpu.myblog.model.User;
import com.github.pagehelper.PageInfo;

public interface ICareService {
    int addCare(int uid,int touid);
    int delCare(int uid,int touid);

    PageInfo<User> getCareListByUid(int pageNum, int pageSize, int touid);
    PageInfo<User> getFansListByTouid(int pageNum, int pageSize, int touid);
}
