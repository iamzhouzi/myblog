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
@TableName("postgood")
public class Postgood implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goodid", type = IdType.AUTO)
    private Integer goodid;

    @TableField("uid")
    private Integer uid;

    @TableField("postid")
    private Integer postid;

    @TableField("articleid")
    private Integer articleid;

    @TableField("createtime")
    private LocalDateTime createtime;


}
