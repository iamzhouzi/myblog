package cn.edu.hbpu.myblog.service.impl;

import cn.edu.hbpu.myblog.mapper.PostMapper;
import cn.edu.hbpu.myblog.model.Post;
import cn.edu.hbpu.myblog.service.IPostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class PostService implements IPostService {
    @Autowired
    private PostMapper postMapper;


    public List<Post> getPostByArticleid(int articleid,int getuid) {
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("articleid",articleid);
        paramMap.put("getuid",getuid);
        return postMapper.getPostByArticleid(paramMap);
    }


    public PageInfo<Post> getPostByTouid(int pageNum, int pageSize, int touid) {
        PageHelper.startPage(pageNum,pageSize);
        List<Post> list = postMapper.getPostByTouid(touid);
        PageInfo<Post> pageInfo=new PageInfo<Post>(list);
        return pageInfo;
    }


    public PageInfo<Post> getPostByUid(int pageNum, int pageSize, int uid) {
        PageHelper.startPage(pageNum,pageSize);
        List<Post> list = postMapper.getPostByUid(uid);
        PageInfo<Post> pageInfo=new PageInfo<Post>(list);
        return pageInfo;
    }


    public int addPost(Post post) {
        post.setCreatetime(LocalDateTime.now());
        postMapper.insert(post);
        return 1;
    }


    public int delPostByPostid(Post post) {
        postMapper.delPostByPostid(post.getPostid());
        return 1;
    }
}
