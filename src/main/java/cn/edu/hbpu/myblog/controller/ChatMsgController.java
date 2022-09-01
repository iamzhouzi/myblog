package cn.edu.hbpu.myblog.controller;

import cn.edu.hbpu.myblog.mapper.UserMapper;
import cn.edu.hbpu.myblog.model.ChatMsg;
import cn.edu.hbpu.myblog.model.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chatMsg")
public class ChatMsgController {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/list")
    public IPage<ChatMsg> list(Integer current, Integer fromUserId, Integer toUserId) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("fromUserId").is(fromUserId), Criteria.where("toUserId").is(toUserId));
        Criteria criteria1 = new Criteria();
        criteria1.andOperator(Criteria.where("fromUserId").is(toUserId), Criteria.where("toUserId").is(fromUserId));
        query.addCriteria(new Criteria().orOperator(criteria, criteria1));
        List<ChatMsg> list = mongoTemplate.find(query,ChatMsg.class);

        for(ChatMsg chatMsg:list) {
            QueryWrapper<User> wrapper = new QueryWrapper<User>();
            wrapper.select("nickname","image")
                .eq("uid",chatMsg.getFromUserId());
            User user = userMapper.selectOne(wrapper);
            chatMsg.setFromNickName(user.getNickname());
            chatMsg.setFromFaceUrl(user.getImage());
            wrapper.clear();
            wrapper.select("nickname","image")
                    .eq("uid",chatMsg.getToUserId());
            user = userMapper.selectOne(wrapper);
            chatMsg.setToNickName(user.getNickname());
            chatMsg.setToFaceUrl(user.getImage());
        }

        IPage<ChatMsg> page = new Page<>(current, 10);
        page.setTotal(list.size());
        page.setPages(page.getTotal()/10);
        page.setRecords(list);
        return page;
    }
}
