package cn.edu.hbpu.myblog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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
@TableName("user")
public class User implements Serializable {

    @TableField(exist = false)
    private MultipartFile imgfile;
    @TableField(exist = false)
    private int articlenum;     //发表文章数
    @TableField(exist = false)
    private int fansnum;        //粉丝数
    @TableField(exist = false)
    private int carenum;        //关注数
    @TableField(exist = false)
    private int getgoodnum;     //获赞数

    @TableField(exist = false)
    private int getuid;         //请求发起人
    @TableField(exist = false)
    private int carenote;    //是否关注

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("nickname")
    private String nickname;

    @TableField("email")
    private String email;

    @TableField("image")
    private String image;

    @TableField("message")
    private String message;

    @TableField("regtime")
    private LocalDateTime regtime;

    @TableField("type")
    private Integer type;

    @TableField("status")
    private Integer status;


}
