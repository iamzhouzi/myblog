package cn.edu.hbpu.myblog.mapper;

import cn.edu.hbpu.myblog.model.Picture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface PictureMapper extends BaseMapper<Picture> {
	/**
     * 查询某新闻的图片
     *
     * @param articleid
     * @return
     */
    @Select("select pictureid,picture from picture where articleid=#{articleid}")
    public List<Picture> getPicListByArticleid(int articleid);
}
