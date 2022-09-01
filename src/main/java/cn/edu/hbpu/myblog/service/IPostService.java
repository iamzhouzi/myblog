package cn.edu.hbpu.myblog.service;

import cn.edu.hbpu.myblog.model.Post;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IPostService {
    List<Post> getPostByArticleid(int articleid, int getuid);
    PageInfo<Post> getPostByTouid(int pageNum, int pageSize, int touid);
    PageInfo<Post> getPostByUid(int pageNum, int pageSize, int uid);
    int addPost(Post post);
    int delPostByPostid(Post post);
}
