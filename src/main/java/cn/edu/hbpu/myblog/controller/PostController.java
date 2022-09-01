package cn.edu.hbpu.myblog.controller;


import cn.edu.hbpu.myblog.model.Post;
import cn.edu.hbpu.myblog.service.IPostService;
import cn.edu.hbpu.myblog.service.IPostgoodService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/post")
@Controller
public class PostController {
    @Autowired
    private IPostService postService;

    @Autowired
    private IPostgoodService postgoodService;


    /**
     * 获取某文章的所有评论
     *
     * @param articleid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPostByArticleid")
    public List<Post> getPostByArticleid(int articleid, int getuid) {
        return postService.getPostByArticleid(articleid, getuid);
    }

    /**
     * 查询回复我的评论
     *
     * @param pageNum
     * @param touid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPostByTouid")
    public PageInfo<Post> addPostNum(int pageNum, int touid) {
        return postService.getPostByTouid(pageNum, 10, touid);
    }

    /**
     * 查询我的评论
     *
     * @param pageNum
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPostByUid")
    public PageInfo<Post> getPostByUid(int pageNum, int uid) {
        return postService.getPostByUid(pageNum, 10, uid);
    }

    /**
     * 在文章内添加评论
     *
     * @param post
     * @return
     */
    @ResponseBody
    @RequestMapping("/addPost")
    public int addPostNum(@RequestBody Post post) {
        return postService.addPost(post);
    }

    /**
     * 删除评论
     *
     * @param post
     * @return
     */
    @ResponseBody
    @RequestMapping("/delPostByPostid")
    public int delPostByPostid(@RequestBody Post post) {
        return postService.delPostByPostid(post);
    }

    /**
     * 给评论点赞
     *
     * @param postid
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/addPostGood")
    public int addPostGood(int postid, int articleid, int uid) {
        return postgoodService.addPostGood(postid, articleid, uid);
    }

    /**
     * 给评论取消点赞
     *
     * @param postid
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/delPostGood")
    public int delPostGood(int postid, int uid) {
        return postgoodService.delPostGood(postid, uid);
    }
}
