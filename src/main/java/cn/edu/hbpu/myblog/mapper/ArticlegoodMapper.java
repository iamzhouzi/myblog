package cn.edu.hbpu.myblog.mapper;

import cn.edu.hbpu.myblog.model.Articlegood;
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
public interface ArticlegoodMapper extends BaseMapper<Articlegood> {
	/**
     * 查询用户点赞文章记录
     *
     * @param uid
     * @return
     */
    @Select("select articleid from articlegood where uid=#{uid} order by createtime desc")
    @Results({
            @Result(property = "articleid", column = "articleid"),
            //查询文章卡片
            @Result(property = "article", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticleMapper.getArticleCardById")
            )
    })
    List<Articlegood> getArticlegoodByUid(int uid);

    /**
     * 查询文章点赞数
     *
     * @param articleid
     * @return
     */
    @Select("select count(uid) from articlegood where articleid=#{articleid}")
    int getNumByUidAndArticleid(int articleid);

    /**
     * 判断文章的点赞人
     *
     * @param param
     * @return
     */
    @Select("select count(goodid) from articlegood where articleid=#{articleid} AND uid=#{uid}")
    int getArticlegoodByUidAndArticleid(Map<String, Object> param);
}
