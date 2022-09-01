package cn.edu.hbpu.myblog.controller;

import cn.edu.hbpu.myblog.mapper.KindMapper;
import cn.edu.hbpu.myblog.model.Kind;
import cn.edu.hbpu.myblog.service.IKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/kind")
@Controller
public class KindController {
    @Autowired
    private IKindService kindService;
    @Autowired
    private KindMapper kindMapper;

    @ResponseBody
    @RequestMapping("/getNumsByKind")
    public List<Kind> getKindNum() {
        return kindMapper.getNumsByKind();
    }


    /**
     * 返回分类列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAll")
    public List<Kind> getAllArticle() {
        return kindService.getKindList();
    }
}
