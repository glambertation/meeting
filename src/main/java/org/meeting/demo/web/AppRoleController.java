package org.meeting.demo.web;
import org.meeting.demo.core.Result;
import org.meeting.demo.core.ResultGenerator;
import org.meeting.demo.model.AppRole;
import org.meeting.demo.service.AppRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/03/28.
*/
@RestController
@RequestMapping("/app/role")
public class AppRoleController {
@Resource
private AppRoleService appRoleService;

@PostMapping("/add")
public Result add(AppRole appRole) {
appRoleService.save(appRole);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/delete")
public Result delete(@RequestParam Integer id) {
appRoleService.deleteById(id);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/update")
public Result update(AppRole appRole) {
appRoleService.update(appRole);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/detail")
public Result detail(@RequestParam Integer id) {
AppRole appRole = appRoleService.findById(id);
return ResultGenerator.genSuccessResult(appRole);
}

@PostMapping("/list")
public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
PageHelper.startPage(page, size);
List<AppRole> list = appRoleService.findAll();
PageInfo pageInfo = new PageInfo(list);
return ResultGenerator.genSuccessResult(pageInfo);
}
}