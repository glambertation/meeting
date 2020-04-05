package org.meeting.demo.web;
import org.meeting.demo.core.Result;
import org.meeting.demo.core.ResultGenerator;
import org.meeting.demo.model.AppUser;
import org.meeting.demo.service.AppUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2020/03/28.
*/
@RestController
@RequestMapping("/app/user")
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