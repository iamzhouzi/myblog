package cn.edu.hbpu.myblog.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document("user_log")
public class Log implements Serializable {
    private Integer id;

    private Integer uid;

    private String operation;

    private LocalDateTime time;
}
