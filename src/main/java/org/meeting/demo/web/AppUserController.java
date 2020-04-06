package org.meeting.demo.web;
import org.meeting.demo.core.Result;
import org.meeting.demo.core.ResultGenerator;
import org.meeting.demo.model.AppUser;
import org.meeting.demo.service.AppUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2020/03/28.
*/
@RestController
@RequestMapping("/app_user")
public class AppUserController {
@Resource
private AppUserService appUserService;

@PostMapping("/add")
public Result add(AppUser appUser) {
appUserService.save(appUser);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/delete")
public Result delete(@RequestParam Integer id) {
appUserService.deleteById(id);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/update")
public Result update(AppUser appUser) {
appUserService.update(appUser);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/detail")
public Result detail(@RequestParam Integer id) {
AppUser appUser = appUserService.findById(id);
return ResultGenerator.genSuccessResult(appUser);
}

@GetMapping("/info")
public Result info() {
    Map userinfo = new HashMap();
    userinfo.put("id", "4291d7da9005377ec9aec4a71ea837f");
    userinfo.put("name", "天野远子");
    userinfo.put("username", "admin");
    userinfo.put("password", "");
    userinfo.put("avatar", "/avatar2.jpg");
    userinfo.put("status", 1);
    userinfo.put("telephone", "");
    userinfo.put("lastLoginIp", "27.154.74.117");
    userinfo.put("lastLoginTime", "1534837621348");
    userinfo.put("creatorId", "admin");
    userinfo.put("createTime", "1497160610259");
    userinfo.put("merchantCode", "TLif2btpzg079h15bk");
    userinfo.put("deleted",0);
    userinfo.put("roleId", "admin");

    
    Map role = new HashMap();
    role.put("id", "admin");
    role.put("name", "管理员");
    role.put("describe", "拥有所有权限");
    role.put("status", 1);
    role.put("creatorId", "system");
    role.put("createTime", "1497160610259");
    role.put("deleted", 0);


    List<Map> permissions = new ArrayList<Map>();
    Map ig = new HashMap();
    ig.put("roleId", "admin");
    ig.put("permissionId", "dashboard");
    ig.put("permissionName", "仪表盘");
    List<Map> ig_action = new ArrayList<>();
    Map i = new HashMap();
    i.put("action","add");
    ig_action.add(i);
    ig.put("actions",ig_action);

    List<Map> ig_action1 = new ArrayList<>();
    Map i1 = new HashMap();
    i1.put("action","add");
    i1.put("describe","新增");
    i1.put("defaultCheck", false);
    ig_action1.add(i1);
    ig.put("actionEntitySet",ig_action1);
    ig.put("actionList",null);
    ig.put("dataAccess",null);

    permissions.add(ig);


    role.put("permissions", permissions);



    userinfo.put("role", role);


    return ResultGenerator.genSuccessResult(userinfo);
}

@PostMapping("/list")
public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
PageHelper.startPage(page, size);
List<AppUser> list = appUserService.findAll();
PageInfo pageInfo = new PageInfo(list);
return ResultGenerator.genSuccessResult(pageInfo);
}

    @PostMapping("/passwd")
    public Result updateHrPasswd(@RequestParam Map<String, Object> info) {
        String oldpass = (String) info.get("oldpass");
        String pass = (String) info.get("pass");
        System.out.println("oldpass");
        System.out.println(oldpass);
        System.out.println("pass");
        System.out.println(pass);
        Integer id = 1;
        System.out.println("这里");

        //大写的AppUserService 是没有实例化的，所以调用方法需要
        if (appUserService.updatePasswd(oldpass, pass, id)) {
            return ResultGenerator.genSuccessResult("更新成功!");
        }
        return ResultGenerator.genSuccessResult("更新失败!");
    }
}