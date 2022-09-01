package cn.edu.hbpu.myblog.service.impl;

import cn.edu.hbpu.myblog.mapper.PostgoodMapper;
import cn.edu.hbpu.myblog.model.Postgood;
import cn.edu.hbpu.myblog.service.IPostgoodService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostgoodService implements IPostgoodService {
    @Autowired
    private PostgoodMapper postgoodMapper;



    public int addPostGood(int postid,int articleid, int uid) {
        Postgood postgood =new Postgood();
        postgood.setUid(uid);
        postgood.setPostid(postid);
        postgood.setArticleid(articleid);
        postgood.setCreatetime(LocalDateTime.now());
        postgoodMapper.insert(postgood);
        return 1;
    }


    public int delPostGood(int postid, int uid) {
        QueryWrapper<Postgood> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("postid",postid);
        queryWrapper.eq("uid",uid);
        postgoodMapper.delete(queryWrapper);
        return 1;
    }
}
