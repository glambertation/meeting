package org.meeting.demo.web;
/*import lombok.Data;*/
import org.meeting.demo.core.Result;
import org.meeting.demo.core.ResultGenerator;
import org.meeting.demo.model.Users;
import org.meeting.demo.service.UsersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2020/03/14.
*/
@RestController
@RequestMapping("/users")
public class UsersController {
@Resource
private UsersService usersService;

@PostMapping("/add")
public Result add(Users users) {
usersService.save(users);
return ResultGenerator.genSuccessResult();
}


    @GetMapping("/adds")
    public Result adds() {
        Users user = new Users();
        user.setUsername("徐哥");
        user.setPassword("123");
        user.setNickName("11");
        user.setRegisterDate(new Date());
        user.setSex(1);
        System.out.println("user");
        System.out.println(user.getUsername());
        usersService.save(user);
        return ResultGenerator.genSuccessResult();
    }

@PostMapping("/delete")
public Result delete(@RequestParam Integer id) {
usersService.deleteById(id);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/update")
public Result update(Users users) {
usersService.update(users);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/detail")
public Result detail(@RequestParam Integer id) {
Users users = usersService.findById(id);
return ResultGenerator.genSuccessResult(users);
}

@PostMapping("/list")
public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
PageHelper.startPage(page, size);
List<Users> list = usersService.findAll();
PageInfo pageInfo = new PageInfo(list);
return ResultGenerator.genSuccessResult(pageInfo);
}

@GetMapping("/listAll")
public Result listAll() {
    List<Users> list = usersService.findAll();
    return ResultGenerator.genSuccessResult(list);
}



}