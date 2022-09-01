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
@TableName("friend")
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "friendid", type = IdType.AUTO)
    private Integer friendid;

    @TableField("uid")
    private Integer uid;

    @TableField("touid")
    private Integer touid;

    @TableField("friendstatus")
    private String friendstatus;

    @TableField("aliasuid")
    private String aliasuid;

    @TableField("aliastouid")
    private String aliastouid;

    @TableField("message")
    private String message;


}
