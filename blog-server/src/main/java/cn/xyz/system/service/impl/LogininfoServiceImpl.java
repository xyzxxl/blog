package cn.xyz.system.service.impl;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.xyz.exception.BusinessException;
import cn.xyz.system.domain.Logininfo;
import cn.xyz.system.domain.Menu;
import cn.xyz.system.dto.LoginDto;
import cn.xyz.system.jwt.JwtUtil;
import cn.xyz.system.jwt.Payload;
import cn.xyz.system.mapper.LogininfoMapper;
import cn.xyz.system.query.LogininfoQuery;
import cn.xyz.system.service.ILogininfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class LogininfoServiceImpl implements ILogininfoService {
    @Autowired
    private LogininfoMapper logininfoMapper;
    @Override
    public List<Logininfo> findAll() {
        return logininfoMapper.findAll();
    }

    @Override
    public Logininfo findOne(Long id) {
        return logininfoMapper.findOne(id);
    }

    @Override
    public void add(Logininfo logininfo) {
        logininfoMapper.add(logininfo);
    }

    @Override
    public void delete(Long id) {
        logininfoMapper.delete(id);
    }

    @Override
    public void update(Logininfo logininfo) {
        logininfoMapper.update(logininfo);
    }

    @Override
    public void patchDelete(Long[] ids) {
        logininfoMapper. patchDelete(ids);
    }

    @Override
    public PageInfo<Logininfo> queryPage(LogininfoQuery logininfoQuery) {
        //开启分页
        PageHelper.startPage(logininfoQuery.getCurrentPage(), logininfoQuery.getPageSize());
        //操作数据库
        List<Logininfo> data = logininfoMapper.queryPage(logininfoQuery);
        return new PageInfo<>(data);
    }

    @Override
    public String accountLogin(LoginDto loginDto) {
        String account = loginDto.getAccount();
        String password = loginDto.getPassword();
        Integer type = loginDto.getType();
        //判断不能为空值
        if (StrUtil.isBlank(account) || StrUtil.isBlank(password)) {
            throw new BusinessException("参数不能为空");
        }
        //校验 账号
        Logininfo logininfo = logininfoMapper.findByAccount(loginDto);//数据库查询出来的
        if (logininfo == null) {//账号不存在
            throw new BusinessException("账号不存在");
        }
        //校验 密码
        String inputMd5Pwd = SecureUtil.md5(password + logininfo.getSalt());
        if (!inputMd5Pwd.equals(logininfo.getPassword())) {
            throw new BusinessException("密码错误");
        }
        //校验 禁用状态
        if (!logininfo.getDisable()) {
            throw new BusinessException("账号被封禁");
        }
        //登录成功 管理员才有 再获取当前登录人的所有权限和所有菜单  设置到payLoad
        Payload payload = new Payload();
        payload.setLogininfo(logininfo);
        if (logininfo.getType() == 0) {//管理员
            //查询当前管理员的所有权限信息
            List<String> permissions = logininfoMapper.findPermissionByLogininfoId(logininfo.getId());
            //查询当前员工的所有菜单信息
            List<Menu> menus = logininfoMapper.findMenuByLogininfoId(logininfo.getId());
            //设置到payload去
            payload.setPermissions(permissions);
            payload.setMenus(menus);
        }
        //调用IwtUtil生成iwtToken并返回
        String token = JwtUtil.generateToken(payload);
        return token;

    }

    public static void main(String[] args) {
        //测试盐值生成，和如何加密
        String salt1 = RandomUtil.randomString(32);

        System.out.println(salt1);


        String pwd1 = "123456";
        //加密盐值 = pwd +salt1/2
        String md5Pwd1 = SecureUtil.md5(pwd1 + salt1);
        System.out.println(md5Pwd1);
    }
}
