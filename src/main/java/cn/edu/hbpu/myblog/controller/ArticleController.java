package cn.edu.hbpu.myblog.controller;

import cn.edu.hbpu.myblog.model.*;
import cn.edu.hbpu.myblog.service.IArticleService;
import cn.edu.hbpu.myblog.service.IArticlegoodService;
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
import java.util.List;

@RequestMapping("/article")
@Controller
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    @Autowired
    private IArticlegoodService articlegoodService;

    @ResponseBody
    @GetMapping("/getPage")
    public IPage<Article> page(Page<Article> page) {
        return articleService.page(page);
    }

    @ResponseBody
    @RequestMapping("/getAll")
    public List<Article> getAllNews() {
        return articleService.selectAllArticle();
    }


    /**
     * 发表文章
     *
     * @param article
     * @return
     */
    @ResponseBody
    @RequestMapping("/addArticle")
    public int addArticle(Article article) throws IOException {
        return articleService.addArticle(article);
    }

    /**
     * 获取我的文章信息
     *
     * @param articleid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMyArticleById")
    public Article getMyArticleById(int articleid) {
        return articleService.getMyArticleById(articleid);
    }

    /**
     * 获取待审核列表(管理员)
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCheckList")
    public List<Article> getCheckList() {
        return articleService.getCheckList();
    }

    /**
     * 获取用户待审核列表
     *
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCheckListByUid")
    public List<Article> getCheckListByUid(int uid) {
        return articleService.getCheckListByUid(uid);
    }

    /**
     * 修改（审核）文章
     *
     * @param article
     * @return
     */
    @ResponseBody
    @RequestMapping("/CheckArticle")
    public int CheckArticle(@RequestBody Article article) {
        return articleService.ChangeArticle(article);
    }

    /**
     * 删除文章
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/delByArticleid")
    public int delByArticleid(int articleid) {
        return articleService.delByArticleid(articleid);
    }

    /**
     * 获取分页文章列表
     *
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPageArticle")
    public PageInfo<Article> getPageNews(int pageNum) {
        return articleService.getPageArticle(pageNum, 10);
    }

    /**
     * 获取分类分页文章列表
     *
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPageArticleByKindid")
    public PageInfo<Article> getPageArticleByKindid(int pageNum, int kindid) {
        return articleService.getPageArticleByKindid(pageNum, 10, kindid);
    }

    /**
     * 关键字获取分页的文章列表
     *
     * @param queryVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPageArticleByKeyword")
    public PageInfo<Article> getPageArticleByKeyword(@RequestBody QueryVo queryVo) {
        return articleService.getPageArticleByKeyword(queryVo.getPageNum(), 10, queryVo.getKeyword());
    }

    /**
     * 获取用户的文章列表
     *
     * @param queryVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPageArticleByUid")
    public PageInfo<Article> getPageArticleByUid(@RequestBody QueryVo queryVo) {
        return articleService.getPageArticleByUid(queryVo.getPageNum(), 10, queryVo.getUid());
    }

    /**
     * 查询用户浏览记录
     *
     * @param queryVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPageArticleviewNoteByUid")
    public PageInfo<Articleview> getPageArticleviewNoteByUid(@RequestBody QueryVo queryVo) {
        return articleService.getPageArticleviewNoteByUid(queryVo.getPageNum(), 10, queryVo.getUid());
    }

    /**
     * 查询用户点赞记录
     *
     * @param queryVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPageArticlegoodNoteByUid")
    public PageInfo<Articlegood> getPageArticlegoodNoteByUid(@RequestBody QueryVo queryVo) {
        return articleService.getPageArticlegoodNoteByUid(queryVo.getPageNum(), 10, queryVo.getUid());
    }

    /**
     * 获取文章详情
     *
     * @param articleid
     * @param getuid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getArticleById")
    public Article getArticleById(int articleid, int getuid) {
        return articleService.getArticleById(articleid, getuid);
    }

    /**
     * 给文章点赞
     *
     * @param articleid
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/addArticleGood")
    public int addArticleGood(int articleid, int uid) {
        return articlegoodService.addArticleGood(articleid, uid);
    }

    /**
     * 给文章取消点赞
     *
     * @param articleid
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/delArticleGood")
    public int delArticleGood(int articleid, int uid) {
        return articlegoodService.delArticleGood(articleid, uid);
    }

}
