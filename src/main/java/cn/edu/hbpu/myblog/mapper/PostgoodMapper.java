package cn.edu.hbpu.myblog.mapper;

import cn.edu.hbpu.myblog.model.Postgood;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface PostgoodMapper extends BaseMapper<Postgood> {
	/**
     * 查询评论点赞数
     *
     * @param postid
     * @return
     */
    @Select("select count(uid) from postgood where postid=#{postid}")
    int getNumByUidAndPostgood(int postid);

    /**
     * 判断评论的点赞人
     *
     * @param param
     * @return
     */
    @Select("select count(goodid) from postgood where postid=#{postid} AND uid=#{uid}")
    int getNoteByUidAndPostgood(Map<String, Object> param);
}
