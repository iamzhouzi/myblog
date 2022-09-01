package cn.edu.hbpu.myblog.service.impl;

import cn.edu.hbpu.myblog.mapper.ArticlegoodMapper;
import cn.edu.hbpu.myblog.model.Articlegood;
import cn.edu.hbpu.myblog.service.IArticlegoodService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticlegoodService implements IArticlegoodService {
    @Autowired
    private ArticlegoodMapper articlegoodMapper;

    public int addArticleGood(int articleid, int uid) {
        Articlegood articlegood = new Articlegood();
        articlegood.setUid(uid);
        articlegood.setArticleid(articleid);
        articlegood.setCreatetime(LocalDateTime.now());
        articlegoodMapper.insert(articlegood);
        return 1;
    }

    public int delArticleGood(int articleid, int uid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("articleid",articleid);
        queryWrapper.eq("uid",uid);
        articlegoodMapper.delete(queryWrapper);
        return 1;
    }
}
