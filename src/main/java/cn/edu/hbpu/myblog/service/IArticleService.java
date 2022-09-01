package cn.edu.hbpu.myblog.service;

import cn.edu.hbpu.myblog.model.Article;
import cn.edu.hbpu.myblog.model.Articlegood;
import cn.edu.hbpu.myblog.model.Articleview;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.io.IOException;
import java.util.List;

public interface IArticleService extends IService<Article> {
    List<Article> selectAllArticle();

    PageInfo<Article> getPageArticle(int pageNum, int pageSize);
    PageInfo<Article> getPageArticleByKindid(int pageNum, int pageSize, int kindid);
    PageInfo<Article> getPageArticleByKeyword(int pageNum, int pageSize,String keyword);
    PageInfo<Article> getPageArticleByUid(int pageNum, int pageSize,int uid);
    PageInfo<Articleview> getPageArticleviewNoteByUid(int pageNum, int pageSize, int uid);
    PageInfo<Articlegood> getPageArticlegoodNoteByUid(int pageNum, int pageSize, int uid);

    Article getArticleById(int articleid, int getuid);

    List<Article> getCheckListByUid(int uid);
    List<Article> getCheckList();

    int delByArticleid(int articleid);
    int addArticle(Article article) throws IOException;
    Article getMyArticleById(int articleid);
    int ChangeArticle(Article article);
}
