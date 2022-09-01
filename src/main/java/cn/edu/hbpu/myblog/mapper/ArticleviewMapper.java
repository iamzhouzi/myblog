package cn.edu.hbpu.myblog.mapper;

import cn.edu.hbpu.myblog.model.Articleview;
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
public interface ArticleviewMapper extends BaseMapper<Articleview> {
	/**
     * 判断文章的浏览人
     *
     * @param param
     * @return
     */
    @Select("select count(viewid)from articleview where articleid=#{articleid} AND uid=#{uid}")
    int getArticleviewByUidAndArticleid(Map<String, Object> param);

    /**
     * 查询文章浏览数
     *
     * @param articleid
     * @return
     */
    @Select("select count(viewid) from articleview where articleid=#{articleid}")
    int getNumByUidAndArticleid(int articleid);

    /**
     * 查询用户浏览文章记录
     *
     * @param uid
     * @return
     */
    @Select("select articleid from articleview where uid=#{uid} order by createtime desc")
    @Results({
            @Result(property = "articleid", column = "articleid"),
            //查询文章卡片
            @Result(property = "article", column = "articleid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticleMapper.getArticleCardById")
            )
    })
    List<Articleview> getArticleviewByUid(int uid);
}
