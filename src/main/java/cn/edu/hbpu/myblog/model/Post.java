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
@TableName("post")
public class Post implements Serializable {
    @TableField(exist = false)
    private String articlecontent;
    @TableField(exist = false)
    private int getuid;

    //二级评论
    @TableField(exist = false)
    private String fromNickname;    //评论人
    @TableField(exist = false)
    private String fromImage;

    @TableField(exist = false)
    private String toNickname;      //被评论人
    @TableField(exist = false)
    private String toImage;

    @TableField(exist = false)
    private Post parentPost;        //此评论所在的一级评论
    @TableField(exist = false)
    private Post beforePost;        //此评论所回复的评论


    //一级评论
    @TableField(exist = false)
    private String nickname;        //评论人
    @TableField(exist = false)
    private String image;
    @TableField(exist = false)
    private List<Post> postList;    //下面的二级评论
    @TableField(exist = false)
    private int postnum;
    @TableField(exist = false)
    private int goodnote;              //查询点赞的人是否有请求人
    @TableField(exist = false)
    private int goodnum;

    private static final long serialVersionUID = 1L;

    @TableId(value = "postid", type = IdType.AUTO)
    private Integer postid;

    @TableField("content")
    private String content;

    @TableField("articleid")
    private Integer articleid;

    @TableField("fromuid")
    private Integer fromuid;

    @TableField("touid")
    private Integer touid;

    @TableField("parentid")
    private Integer parentid;

    @TableField("topostid")
    private Integer topostid;

    @TableField("posttype")
    private Integer posttype;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @TableField("topstatus")
    private Integer topstatus;

    @TableField("createtime")
    private LocalDateTime createtime;


}
