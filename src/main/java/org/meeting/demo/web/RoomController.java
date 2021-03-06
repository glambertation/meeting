package org.meeting.demo.web;
import org.meeting.demo.core.Result;
import org.meeting.demo.core.ResultGenerator;
import org.meeting.demo.model.Room;
import org.meeting.demo.service.RoomService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/05/13.
*/
@RestController
@RequestMapping("/room")
public class RoomController {
@Resource
private RoomService roomService;

@PostMapping("/add")
public Result add(Room room) {
roomService.save(room);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/delete")
public Result delete(@RequestParam Integer id) {
roomService.deleteById(id);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/update")
public Result update(Room room) {
roomService.update(room);
return ResultGenerator.genSuccessResult();
}

@PostMapping("/detail")
public Result detail(@RequestParam Integer id) {
Room room = roomService.findById(id);
return ResultGenerator.genSuccessResult(room);
}

@PostMapping("/list")
public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
PageHelper.startPage(page, size);
List<Room> list = roomService.findAll();
PageInfo pageInfo = new PageInfo(list);
return ResultGenerator.genSuccessResult(pageInfo);
}
}