package org.meeting.demo.service.impl;

import org.meeting.demo.core.ResultGenerator;
import org.meeting.demo.dao.AppUserMapper;
import org.meeting.demo.model.AppUser;
import org.meeting.demo.service.AppUserService;
import org.meeting.demo.core.AbstractService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
* Created by CodeGenerator on 2020/03/28.
*/
@Service
@Transactional
public class AppUserServiceImpl extends AbstractService<AppUser> implements AppUserService {
@Resource
private AppUserMapper appUserMapper;

    // 登录给auth获取数据库数据
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userdetail = null;
        AppUser user = new AppUser();
        user.setUsername(username);
        AppUser appUser= appUserMapper.selectOne(user);
        System.out.println("appUser");
        System.out.println(appUser);
        Collection<GrantedAuthority> authList = getAuthorities();
        if(appUser!=null){
            userdetail = new User(username, appUser.getPassword(),true,true,true,true,authList);

        }
        if (appUser == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        /*appUser.setRoles(appUserMapper.getHrRolesById(appUser.getId()));*/
        System.out.println("userdetail");
        System.out.println(ResultGenerator.genSuccessResult(userdetail));
        return userdetail;
    }
    // 登录模块，手动写权限数据
    private Collection<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authList;
    }

    //修改密码
    public boolean updatePasswd(String oldpass, String pass, Integer id) {
        AppUser appUser = appUserMapper.selectByPrimaryKey(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(/*appUser.getPassword()*/);
        System.out.println(appUser.getPassword());
        System.out.println(encoder.matches(oldpass, appUser.getPassword()));
        if (encoder.matches(oldpass, appUser.getPassword())) {
            String encodePass = encoder.encode(pass);
            AppUser newpass = new AppUser();
            newpass.setId(id);
            newpass.setPassword(encodePass);
            Integer result = appUserMapper.updateByPrimaryKeySelective(newpass);
            if (result == 1) {
                return true;
            }
        }
        return false;
    }
}