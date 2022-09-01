package cn.edu.hbpu.myblog.mapper;

import cn.edu.hbpu.myblog.model.Kind;
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
public interface KindMapper extends BaseMapper<Kind> {
    @Select("SELECT COUNT(n.articleid) as nums,k.content from article n "
            +"LEFT JOIN kind k on n.kindid=k.kindid GROUP BY n.kindid ")
    List<Kind> getNumsByKind();
}
