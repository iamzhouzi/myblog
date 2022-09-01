package cn.edu.hbpu.myblog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 舟师
 * @since 2022-05-14
 */
@Getter
@Setter
@TableName("articlegood")
public class Articlegood implements Serializable {
    @TableField(exist = false)
    private Article article;

    private static final long serialVersionUID = 1L;

    @TableId(value = "goodid", type = IdType.AUTO)
    private Integer goodid;

    /**
     * 点赞人
     */
    @TableField("uid")
    private Integer uid;

    /**
     * 文章
     */
    @TableField("articleid")
    private Integer articleid;

    /**
     * 点赞时间
     */
    @TableField("createtime")
    private LocalDateTime createtime;


}
