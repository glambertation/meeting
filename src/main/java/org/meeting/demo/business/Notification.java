package org.meeting.demo.business;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.meeting.demo.core.Result;
import org.meeting.demo.core.ResultGenerator;
import org.meeting.demo.model.Chatmsg;
import org.meeting.demo.service.ChatmsgService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Notification")
public class Notification {

    @Resource
    private ChatmsgService chatmsgService;

    @PostMapping("/list")
    public Result list(String username, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Map data = new HashMap();

        List<Chatmsg> chatmsgs = null;
        Condition condition = new Condition(Chatmsg.class);
        condition.createCriteria()
                .andLike("toid", '%'+username+'%');
        condition.orderBy("status").asc().orderBy("date");
        chatmsgs = chatmsgService.findByCondition(condition);

        PageInfo pageInfo = new PageInfo(chatmsgs);

        return ResultGenerator.genSuccessResult(pageInfo);
    }

    // 未读信息数
    public Result unreadCount(String username) {
        Map data = new HashMap();

        List<Chatmsg> chatmsgs = null;
        Condition condition = new Condition(Chatmsg.class);
        condition.createCriteria()
                .andLike("toid", '%'+username+'%')
                .andEqualTo("status", "0");
        chatmsgs = chatmsgService.findByCondition(condition);
        data.put("unreadCount", chatmsgs.size());

        return ResultGenerator.genSuccessResult(data);
    }

    // 已读信息
    public Result read(String id, String username) {

        Map data = new HashMap();
        int msg_id = Integer.parseInt(id);

        Chatmsg chatmsg = chatmsgService.findById(msg_id);
        if(chatmsg == null) {
            data.put("errormsg","chatmsg not found");
            return ResultGenerator.genSuccessResult(data);
        }
        if(chatmsg.getToid().indexOf(username) == -1) {
            data.put("errormsg","not user's msg");
            return ResultGenerator.genSuccessResult(data);
        }

        chatmsg.setStatus("1");
        chatmsgService.update(chatmsg);
        data.put("msg", "success");

        return ResultGenerator.genSuccessResult(data);
    }


}
