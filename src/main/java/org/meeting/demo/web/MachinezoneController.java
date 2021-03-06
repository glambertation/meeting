package org.meeting.demo.web;
import org.meeting.demo.core.Result;
import org.meeting.demo.core.ResultGenerator;
import org.meeting.demo.model.Machinezone;
import org.meeting.demo.service.MachinezoneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/03/26.
*/
@RestController
@RequestMapping("/machinezone")
public class MachinezoneController {
@Resource
private MachinezoneService machinezoneService;

@PostMapping("/add")
public Result add(Machinezone machinezone) {
machinezoneService.save(machinezone);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/delete")
public Result delete(@RequestParam Integer id) {
machinezoneService.deleteById(id);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/update")
public Result update(Machinezone machinezone) {
machinezoneService.update(machinezone);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/detail")
public Result detail(@RequestParam Integer id) {
Machinezone machinezone = machinezoneService.findById(id);
return ResultGenerator.genSuccessResult(machinezone);
}

@PostMapping("/list")
public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
PageHelper.startPage(page, size);
List<Machinezone> list = machinezoneService.findAll();
PageInfo pageInfo = new PageInfo(list);
return ResultGenerator.genSuccessResult(pageInfo);
}
}