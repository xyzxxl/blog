package cn.xyz.blog.service.impl;


import cn.xyz.blog.domain.Param;
import cn.xyz.blog.mapper.ParamMapper;
import cn.xyz.blog.query.ParamQuery;
import cn.xyz.blog.service.IParamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class ParamServiceImpl implements IParamService {
    @Autowired
    private ParamMapper paramMapper;
    @Override
    public List<Param> findAll() {
        return paramMapper.findAll();
    }

    @Override
    public Param findOne(Long id) {
        return paramMapper.findOne(id);
    }

    @Override
    public void add(Param param) {
        paramMapper.add(param);
    }

    @Override
    public void delete(Long id) {

    }


    @Override
    public void update(Param param) {
        paramMapper.update(param);
    }

    @Override
    public void patchDelete(Long[] ids) {
        paramMapper. patchDelete(ids);
    }

    @Override
    public PageInfo<Param> queryPage(ParamQuery paramQuery) {
        //开启分页
        PageHelper.startPage(paramQuery.getCurrentPage(), paramQuery.getPageSize());
        //操作数据库
        List<Param> data = paramMapper.queryPage(paramQuery);
        return new PageInfo<>(data);
    }

    @Override
    public Map<String, Object> getParams() {
        List<Param> params = paramMapper.findAll();
        return params.stream().collect(Collectors.toMap(Param::getParamKey, Param::getParamValue));
//        Map<String, Object> map = new HashMap<>();
//        for (Param param : params) {
//            map.put(param.getParamKey(), param.getParamValue());
//        }
//        return map;
    }


}
