package cn.edu.hbpu.myblog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("kind")
public class Kind implements Serializable {
    @TableField(exist = false)
    private transient Long nums;

    private static final long serialVersionUID = 1L;

    @TableId(value = "kindid", type = IdType.AUTO)
    private Integer kindid;

    @TableField("content")
    private String content;


}
