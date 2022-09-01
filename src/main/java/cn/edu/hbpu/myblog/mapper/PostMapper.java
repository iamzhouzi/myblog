package cn.edu.hbpu.myblog.mapper;

import cn.edu.hbpu.myblog.model.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 舟师
 * @since 2022-05-14
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {
	/**
     * 删除评论和其子评论
     *
     * @param postid
     */
    @Delete("delete from post where postid=#{postid} OR parentid=#{postid}")
    void delPostByPostid(int postid);

    /**
     * 通过ID查一条评论
     *
     * @param postid
     * @return
     */
    @Select("select p.*,f.nickname as fromNickname,f.image as fromImage," +
            "t.nickname as toNickname,t.image as toImage from post p " +
            "LEFT JOIN user f on p.fromuid=f.uid " +
            "LEFT JOIN user t on p.touid=t.uid " +
            "where p.postid=#{postid} " +
            "order by postid desc")
    Post getPostByPostid(int postid);

    /**
     * 查询我的评论
     *
     * @param uid
     * @return
     */
    @Select("select p.*,a.content as articlecontent from post p " +
            "LEFT JOIN article a on a.articleid=p.articleid " +
            "where p.fromuid=#{uid} " +
            "order by postid desc")
    List<Post> getPostByUid(int uid);

    /**
     * 查询回复我的评论
     *
     * @param touid
     * @return
     */
    @Select("select p.*,f.nickname as fromNickname,f.image as fromImage,a.content as articlecontent " +
            "from post p " +
            "LEFT JOIN user f on p.fromuid=f.uid " +
            "LEFT JOIN article a on a.articleid=p.articleid " +
            "where p.touid=#{touid} AND p.fromuid!=#{touid} " +
            "order by postid desc")
    @Results({
            @Result(property = "parentid", column = "parentid"),
            //查询所在的一级评论
            @Result(property = "parentPost", column = "parentid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.PostMapper.getPostByPostid")
            ),
            //查询此评论所回复的评论
            @Result(property = "beforePost", column = "topostid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.PostMapper.getPostByPostid")
            )
    })
    List<Post> getPostByTouid(int touid);

    /**
     * 查询文章评论数
     *
     * @param articleid
     * @return
     */
    @Select("select count(postid) from post where articleid=#{articleid}")
    int getPostNumByArticleid(int articleid);

    /**
     * 查询评论的所有二级评论
     *
     * @param parentid
     * @return
     */
    @Select("select p.*,f.nickname as fromNickname,f.image as fromImage," +
            "t.nickname as toNickname,t.image as toImage from post p " +
            "LEFT JOIN user f on p.fromuid=f.uid " +
            "LEFT JOIN user t on p.touid=t.uid " +
            "where p.parentid=#{parentid} and p.postType=2 " +
            "order by postid desc")
    List<Post> getPostByTopostid(int parentid);

    /**
     * 查询某文章所有评论
     *
     * @param param
     * @return
     */
    @Select("select #{getuid} as getuid,p.*,f.nickname,f.image from post p " +
            "LEFT JOIN user f on p.fromuid=f.uid " +
            "where p.articleid=#{articleid} and p.posttype=1 " +
            "order by postid desc")
    @Results({
            @Result(property = "postid", column = "postid"),
            //查询二级评论列表
            @Result(property = "postList", javaType = List.class, column = "postid",
                    many = @Many(select = "cn.edu.hbpu.myblog.mapper.PostMapper.getPostByTopostid")
            ),
            //查询点赞人
            @Result(property = "goodnote", column = "{uid=getuid,postid=postid}",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.PostgoodMapper.getNoteByUidAndPostgood")
            ),
            //查询点赞数量
            @Result(property = "goodnum", column = "postid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.PostgoodMapper.getNumByUidAndPostgood")
            )
    })
    List<Post> getPostByArticleid(Map<String, Object> param);
}
