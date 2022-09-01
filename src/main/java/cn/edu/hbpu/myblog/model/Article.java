package cn.edu.hbpu.myblog.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@TableName("article")
public class Article implements Serializable {
    @TableField(exist = false)
    private MultipartFile picturefile;
    @TableField(exist = false)
    private String nickname;
    @TableField(exist = false)
    private String image;
    @TableField(exist = false)
    private String kindname;
    @TableField(exist = false)
    private List<Picture> picList;
    @TableField(exist = false)
    private List<Post> postList;
    @TableField(exist = false)
    private int postnum;
    @TableField(exist = false)
    private int getuid;

    @TableField(exist = false)
    private int viewnote;
    @TableField(exist = false)
    private int viewnum;
    @TableField(exist = false)
    private int goodnote;
    @TableField(exist = false)
    private int goodnum;

    private static final long serialVersionUID = 1L;

    @TableId(value = "articleid", type = IdType.AUTO)
    private Integer articleid;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("picture")
    private String picture;

    @TableField("video")
    private String video;

    @TableField("fromarticleid")
    private Integer fromarticleid;

    @TableField("uid")
    private Integer uid;

    @TableField("kindid")
    private Integer kindid;

    @TableField("sortid")
    private Integer sortid;

    @TableField("sharenum")
    private Integer sharenum;

    @TableField("mediatype")
    private Integer mediatype;

    @TableField("poststatus")
    private Integer poststatus;

    @TableField("articletype")
    private Integer articletype;

    @TableField("articlestatus")
    private Integer articlestatus;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @TableField("createtime")
    private LocalDateTime createtime;

    @TableField("updatetime")
    private LocalDateTime updatetime;

    @TableField("lastpost")
    private LocalDateTime lastpost;


}
