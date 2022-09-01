package cn.edu.hbpu.myblog.mapper;

import cn.edu.hbpu.myblog.model.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 舟师
 * @since 2022-05-14
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
	/**
     * 返回一篇文章卡片信息
     *
     * @param articleid
     * @return
     */
    @Select("select n.*,u.nickname,u.image from article n " +
            "LEFT JOIN user u on n.uid=u.uid " +
            "where n.articlestatus=1 and n.articleid=#{articleid} AND n.deleted=0 ")
    @Results({
            @Result(property = "articleid", column = "articleid"),
            //查询评论数
            @Result(property = "postnum", column = "articleid",
                    many = @Many(select = "cn.edu.hbpu.myblog.mapper.PostMapper.getPostNumByArticleid")
            ),
            //查询浏览数
            @Result(property = "viewnum", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticleviewMapper.getNumByUidAndArticleid")
            ),
            //查询点赞数
            @Result(property = "goodnum", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticlegoodMapper.getNumByUidAndArticleid")
            )
    })
    Article getArticleCardById(int articleid);

    /**
     * 返回文章详情
     *
     * @param articleid
     * @param getuid
     * @return
     */
    @Select("select #{getuid} as getuid,n.*,k.content AS kindname,k.kindid,u.nickname,u.image from article n " +
            "LEFT JOIN kind k on n.kindid=k.kindid " +
            "LEFT JOIN user u on n.uid=u.uid " +
            "where n.articleid=#{articleid} and n.articlestatus=1 AND n.deleted=0")
    @Results({
            @Result(property = "articleid", column = "articleid"),
            //查询图片列表
            @Result(property = "picList", javaType = List.class, column = "articleid",
                    many = @Many(select = "cn.edu.hbpu.myblog.mapper.PictureMapper.getPicListByArticleid")
            ),
            //查询请求人是否点赞
            @Result(property = "goodnote", column = "{uid=getuid,articleid=articleid}",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticlegoodMapper.getArticlegoodByUidAndArticleid")
            ),
            //查询点赞数
            @Result(property = "goodnum", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticlegoodMapper.getNumByUidAndArticleid")
            ),
            //查询评论数
            @Result(property = "postnum", column = "articleid",
                    many = @Many(select = "cn.edu.hbpu.myblog.mapper.PostMapper.getPostNumByArticleid")
            ),
            //查询浏览数
            @Result(property = "viewnum", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticleviewMapper.getNumByUidAndArticleid")
            )
    })
    Article getArticleById(@Param("articleid") int articleid, @Param("getuid") int getuid);

    /**
     * 返回文章列表
     *
     * @return
     */
    @Select("select n.*,u.nickname,u.image from article n "
            + "LEFT JOIN user u on n.uid=u.uid " +
            "where n.articlestatus=1 AND n.deleted=0 order by n.articleid desc")
    @Results({
            //把articleid重新加入
            @Result(property = "articleid", column = "articleid"),
            //查询评论数
            @Result(property = "postnum", column = "articleid",
                    many = @Many(select = "cn.edu.hbpu.myblog.mapper.PostMapper.getPostNumByArticleid")
            ),
            //查询浏览数
            @Result(property = "viewnum", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticleviewMapper.getNumByUidAndArticleid")
            ),
            //查询点赞数
            @Result(property = "goodnum", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticlegoodMapper.getNumByUidAndArticleid")
            )
    })
    List<Article> getArticleList();

    /**
     * 返回某分类的文章列表
     *
     * @param kindid
     * @return
     */
    @Select("select n.*,u.nickname,u.image from article n "
            + "LEFT JOIN user u on n.uid=u.uid " +
            "where n.articlestatus=1 AND n.kindid=#{kindid} AND n.deleted=0 "
            + "order by n.articleid desc")
    @Results({
            //把articleid重新加入
            @Result(property = "articleid", column = "articleid"),
            //查询评论数
            @Result(property = "postnum", column = "articleid",
                    many = @Many(select = "cn.edu.hbpu.myblog.mapper.PostMapper.getPostNumByArticleid")
            ),
            //查询浏览数
            @Result(property = "viewnum", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticleviewMapper.getNumByUidAndArticleid")
            ),
            //查询点赞数
            @Result(property = "goodnum", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticlegoodMapper.getNumByUidAndArticleid")
            )
    })
    List<Article> getArticleListByKindid(int kindid);

    /**
     * 返回某关键字的文章列表
     *
     * @param keyword
     * @return
     */
    @Select("select n.*,u.nickname,u.image from article n "
            + "LEFT JOIN user u on n.uid=u.uid " +
            "where n.articlestatus=1 AND n.content like #{keyword} AND n.deleted=0 "
            + "order by n.articleid desc")
    @Results({
            //把articleid重新加入
            @Result(property = "articleid", column = "articleid"),
            //查询评论数
            @Result(property = "postnum", column = "articleid",
                    many = @Many(select = "cn.edu.hbpu.myblog.mapper.PostMapper.getPostNumByArticleid")
            ),
            //查询浏览数
            @Result(property = "viewnum", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticleviewMapper.getNumByUidAndArticleid")
            ),
            //查询点赞数
            @Result(property = "goodnum", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticlegoodMapper.getNumByUidAndArticleid")
            )
    })
    List<Article> getPageArticleByKeyword(String keyword);

    /**
     * 返回某用户发表的的文章数
     *
     * @param uid
     * @return
     */
    @Select("select count(articleid) from article where articlestatus=1 AND uid=#{uid} AND deleted=0 ")
    int getNumByMyuid(int uid);

    /**
     * 返回某用户的文章列表
     *
     * @param uid
     * @return
     */
    @Select("select n.*,u.nickname,u.image from article n "
            + "LEFT JOIN user u on n.uid=u.uid " +
            "where n.articlestatus=1 AND n.uid=#{uid} AND n.deleted=0 "
            + "order by n.articleid desc")
    @Results({
            //把articleid重新加入
            @Result(property = "articleid", column = "articleid"),
            //查询评论数
            @Result(property = "postnum", column = "articleid",
                    many = @Many(select = "cn.edu.hbpu.myblog.mapper.PostMapper.getPostNumByArticleid")
            ),
            //查询浏览数
            @Result(property = "viewnum", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticleviewMapper.getNumByUidAndArticleid")
            ),
            //查询点赞数
            @Result(property = "goodnum", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticlegoodMapper.getNumByUidAndArticleid")
            )
    })
    List<Article> getPageArticleByUid(int uid);

    /**
     * 返回某用户审核中的文章列表
     *
     * @param uid
     * @return
     */
    @Select("select n.*,u.nickname,u.image from article n "
            + "LEFT JOIN user u on n.uid=u.uid " +
            "where (n.articlestatus=2 OR n.articlestatus=3) AND n.uid=#{uid} AND n.deleted=0 "
            + "order by n.articleid desc")
    List<Article> getCheckListByUid(int uid);

    /**
     * 返回用户审核中的文章列表（管理员）
     *
     * @return
     */
    @Select("select n.*,u.nickname,u.image from article n "
            + "LEFT JOIN user u on n.uid=u.uid " +
            "where n.articlestatus=2 AND n.deleted=0 "
            + "order by n.articleid desc")
    List<Article> getCheckList();
}
