package cn.edu.hbpu.myblog.controller;

import cn.edu.hbpu.myblog.model.ChatMsg;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WsController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @Autowired
    MongoTemplate mongoTemplate;

    @MessageMapping("/chat")
    void chat(ChatMsg msg) {
        msg.setId(ObjectId.get());
        msg.setCreateTime(LocalDateTime.now());
        msg.setType("1");
        mongoTemplate.save(msg);
        messagingTemplate.convertAndSendToUser(msg.getToUserId().toString(),"/queue/chat", msg);
    }
}
