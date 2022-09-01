package cn.edu.hbpu.myblog.listener;

//import cn.edu.hbpu.myblog.model.Log;
//import cn.edu.hbpu.myblog.model.User;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Slf4j
//@Component
//@RocketMQMessageListener(topic = "login-log",consumerGroup = "news-log-consumer")
//public class LogConsumer implements RocketMQListener<User> {
//
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    @Override
//    public void onMessage(User u) {
//        log.info("Receive message：" + u);
//        System.out.println("用户"+u.getUsername()+"登录");
//        Log log = new Log();
//        log.setUid(u.getUid());
//        log.setTime(LocalDateTime.now());
//        log.setOperation("登录");
//        mongoTemplate.save(log);
//
//    }
//}
