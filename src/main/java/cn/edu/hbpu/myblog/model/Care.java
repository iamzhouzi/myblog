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
@TableName("care")
public class Care implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "careid", type = IdType.AUTO)
    private Integer careid;

    @TableField("uid")
    private Integer uid;

    @TableField("touid")
    private Integer touid;

    @TableField("aliasuid")
    private String aliasuid;

    @TableField("aliastouid")
    private String aliastouid;

    @TableField("caretype")
    private String caretype;

    @TableField("carestatus")
    private String carestatus;

    @TableField("createtime")
    private LocalDateTime createtime;


}
