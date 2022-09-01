package cn.edu.hbpu.myblog.mapper;

import cn.edu.hbpu.myblog.model.User;
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
public interface UserMapper extends BaseMapper<User> {
	/**
     * 返回我的用户信息
     *
     * @param uid
     * @return
     */
    @Select("select u.* from user u where uid=#{uid}")
    @Results({
            @Result(property = "uid", column = "uid"),
            //查询关注数
            @Result(property = "carenum", column = "uid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.CareMapper.getCareNumByUid")
            ),
            //查询粉丝数
            @Result(property = "fansnum", column = "uid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.CareMapper.getFansNumByTouid")
            ),
            //查询发表文章数
            @Result(property = "articlenum", column = "uid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.ArticleMapper.getNumByMyuid")
            )
    })
    User getUserByMyuid(int uid);

    /**
     * 返回一个用户的详细信息
     *
     * @param uid
     * @param getuid
     * @return
     */
    @Select("select #{getuid} as getuid, u.* from user u where uid=#{uid}")
    @Results({
            @Result(property = "uid", column = "uid"),
            //查询关注数
            @Result(property = "carenum", column = "uid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.CareMapper.getCareNumByUid")
            ),
            //查询粉丝数
            @Result(property = "fansnum", column = "uid",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.CareMapper.getFansNumByTouid")
            ),
            //查询是否已关注
            @Result(property = "carenote", column = "{uid=getuid,touid=uid}",
                    one = @One(select = "cn.edu.hbpu.myblog.mapper.CareMapper.getNoteByUidAndTouid")
            ),
    })
    User getUserByUid(@Param("uid") int uid, @Param("getuid") int getuid);

}
