package org.meeting.demo.web;
import org.meeting.demo.core.Result;
import org.meeting.demo.core.ResultGenerator;
import org.meeting.demo.model.Eventhandle;
import org.meeting.demo.service.EventhandleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/03/30.
*/
@RestController
@RequestMapping("/eventhandle")
public class EventhandleController {
@Resource
private EventhandleService eventhandleService;

@PostMapping("/add")
public Result add(Eventhandle eventhandle) {
eventhandleService.save(eventhandle);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/delete")
public Result delete(@RequestParam Integer id) {
eventhandleService.deleteById(id);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/update")
public Result update(Eventhandle eventhandle) {
eventhandleService.update(eventhandle);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/detail")
public Result detail(@RequestParam Integer id) {
Eventhandle eventhandle = eventhandleService.findById(id);
return ResultGenerator.genSuccessResult(eventhandle);
}

@PostMapping("/list")
public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
PageHelper.startPage(page, size);
List<Eventhandle> list = eventhandleService.findAll();
PageInfo pageInfo = new PageInfo(list);
return ResultGenerator.genSuccessResult(pageInfo);
}
}