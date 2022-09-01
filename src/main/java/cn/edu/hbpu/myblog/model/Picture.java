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
@TableName("picture")
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pictureid", type = IdType.AUTO)
    private Integer pictureid;

    @TableField("picture")
    private String picture;

    @TableField("articleid")
    private Integer articleid;


}
