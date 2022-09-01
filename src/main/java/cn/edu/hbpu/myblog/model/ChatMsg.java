package cn.edu.hbpu.myblog.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document("chat_msg")
public class ChatMsg implements Serializable {
    private String fromNickName;
    private String toNickName;
    private String fromFaceUrl;
    private String toFaceUrl;

    private String content;
    private Integer fromUserId;
    private Integer toUserId;
    private ObjectId id;
    private String type;
    private LocalDateTime createTime;
}
