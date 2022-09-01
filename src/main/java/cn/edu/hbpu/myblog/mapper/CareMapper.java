package cn.edu.hbpu.myblog.mapper;

import cn.edu.hbpu.myblog.model.Care;
import cn.edu.hbpu.myblog.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface CareMapper extends BaseMapper<Care> {
	/**
     * 查询关注列表
     *
     * @param uid
     * @return
     */
    @Select("select u.* from user u " +
            "LEFT JOIN care c on c.touid=u.uid " +
            "where c.uid=#{uid} order by createtime")
    List<User> getCareListByUid(int uid);

    /**
     * 查询粉丝列表
     *
     * @param uid
     * @return
     */
    @Select("select u.* from user u " +
            "LEFT JOIN care c on c.uid=u.uid " +
            "where c.touid=#{uid} order by createtime")
    List<User> getFansListByTouid(int uid);

    /**
     * 查询粉丝数
     *
     * @param uid
     * @return
     */
    @Select("select count(careid) from care where touid=#{uid}")
    int getFansNumByTouid(int uid);

    /**
     * 查询关注数
     *
     * @param uid
     * @return
     */
    @Select("select count(careid) from care where uid=#{uid}")
    int getCareNumByUid(int uid);

    /**
     * 判断是否已关注
     *
     * @param param
     * @return
     */
    @Select("select careid from care where uid=#{uid} AND touid=#{touid}")
    int getNoteByUidAndTouid(Map<String, Object> param);
}
