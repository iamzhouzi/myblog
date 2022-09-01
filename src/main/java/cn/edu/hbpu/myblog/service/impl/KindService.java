package cn.edu.hbpu.myblog.service.impl;

import cn.edu.hbpu.myblog.mapper.KindMapper;
import cn.edu.hbpu.myblog.model.Kind;
import cn.edu.hbpu.myblog.service.IKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KindService implements IKindService {
    @Autowired
    private KindMapper kindMapper;

    public List<Kind> getKindList() {
        return kindMapper.selectList(null);
    }
}
