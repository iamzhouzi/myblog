package cn.edu.hbpu.myblog.service.impl;

import cn.edu.hbpu.myblog.mapper.*;
import cn.edu.hbpu.myblog.model.*;
import cn.edu.hbpu.myblog.service.IArticleService;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.setting.dialect.Props;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticlegoodMapper articlegoodMapper;

    @Autowired
    private ArticleviewMapper articleviewMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostgoodMapper postgoodMapper;

    public Article getMyArticleById(int articleid) {
        return articleMapper.selectById(articleid);
    }

    public List<Article> getCheckListByUid(int uid){
        return articleMapper.getCheckListByUid(uid);
    }

    public List<Article> getCheckList(){
        return articleMapper.getCheckList();
    }

    public int addArticle(Article article) throws IllegalStateException, IOException {
        if(article.getMediatype()!=0) {
            MultipartFile file = article.getPicturefile();
            //判断文件是否为空,空则返回失败信息
            if(file.isEmpty()) {
                return 0;
            }
            //获取文件上传路径
            Props prop = Props.getProp("db.properties");
            String path =prop.getStr("uploadPath");
            //获取原文件名
            String fileName = file.getOriginalFilename();
            String newName= UUID.randomUUID().toString(true)+"."+ FileNameUtil.extName(fileName);
            //创建文件实例
            File filePath = new File(path,newName);
            //如果文件目录不存在，创建目录
            if(!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录" +filePath);
            }
            //写入文件
            file.transferTo(filePath);
            article.setPicture(newName);
        }
        article.setSharenum(0);
        article.setArticletype(1);
        article.setArticlestatus(1);
        article.setDeleted(0);
        article.setCreatetime(LocalDateTime.now());
        article.setUpdatetime(LocalDateTime.now());
        article.setLastpost(LocalDateTime.now());
        articleMapper.insert(article);
        return 1;
    }

    public int ChangeArticle(Article article) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("articleid",article.getArticleid());
        article.setArticlestatus(2);
        article.setUpdatetime(LocalDateTime.now());
        article.setLastpost(LocalDateTime.now());
        articleMapper.update(article,queryWrapper);
        return 1;
    }

    public int delByArticleid(int articleid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("articleid",articleid);

        articleMapper.deleteById(articleid);

        articleviewMapper.delete(queryWrapper);

        articlegoodMapper.delete(queryWrapper);

        postMapper.delete(queryWrapper);

        postgoodMapper.delete(queryWrapper);

        return 1;
    }

    public List<Article> selectAllArticle() {
        return articleMapper.selectList(null);
    }

    public PageInfo<Article> getPageArticle(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Article> list = articleMapper.getArticleList();
        PageInfo<Article> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public PageInfo<Article> getPageArticleByKindid(int pageNum, int pageSize,int kindid) {
        PageHelper.startPage(pageNum,pageSize);
        List<Article> list = articleMapper.getArticleListByKindid(kindid);
        PageInfo<Article> pageInfo=new PageInfo<Article>(list);
        return pageInfo;
    }

    public PageInfo<Article> getPageArticleByKeyword(int pageNum, int pageSize, String keyword) {
        PageHelper.startPage(pageNum,pageSize);
        List<Article> list = articleMapper.getPageArticleByKeyword("%"+keyword+"%");
        PageInfo<Article> pageInfo=new PageInfo<Article>(list);
        return pageInfo;
    }

    public PageInfo<Article> getPageArticleByUid(int pageNum, int pageSize,int uid) {
        PageHelper.startPage(pageNum,pageSize);
        List<Article> list = articleMapper.getPageArticleByUid(uid);
        PageInfo<Article> pageInfo=new PageInfo<Article>(list);
        return pageInfo;
    }

    public PageInfo<Articleview> getPageArticleviewNoteByUid(int pageNum, int pageSize,int uid) {
        PageHelper.startPage(pageNum,pageSize);
        List<Articleview> list = articleviewMapper.getArticleviewByUid(uid);
        PageInfo<Articleview> pageInfo=new PageInfo<Articleview>(list);
        return pageInfo;
    }

    public PageInfo<Articlegood> getPageArticlegoodNoteByUid(int pageNum, int pageSize, int uid) {
        PageHelper.startPage(pageNum,pageSize);
        List<Articlegood> list = articlegoodMapper.getArticlegoodByUid(uid);
        PageInfo<Articlegood> pageInfo=new PageInfo<Articlegood>(list);
        return pageInfo;
    }

    public Article getArticleById(int articleid, int getuid) {
        //游客不增加浏览量
        if(getuid!=0) {
            //首次查看则增加浏览量
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("articleid",articleid);
            paramMap.put("uid", getuid);
            int n = articleviewMapper.getArticleviewByUidAndArticleid(paramMap);
            System.out.println(n);
            if(n==0) {
                Articleview articleview = new Articleview();
                articleview.setArticleid(articleid);
                articleview.setUid(getuid);
                articleview.setCreatetime(LocalDateTime.now());
                articleviewMapper.insert(articleview);
            }
        }
        //获取文章信息
        return articleMapper.getArticleById(articleid,getuid);
    }
}
