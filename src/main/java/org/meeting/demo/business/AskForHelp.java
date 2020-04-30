package org.meeting.demo.business;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.meeting.demo.core.Result;
import org.meeting.demo.core.ResultGenerator;
import org.meeting.demo.model.*;
import org.meeting.demo.rabbitmq.direct.DirectSender2;
import java.security.Principal;

import org.meeting.demo.service.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.*;


/**
* Created by CodeGenerator on 2020/03/14.
*/
@RestController
@RequestMapping("/AskForHelp")
public class AskForHelp {
    @Resource
    private MachinenumberService machinenumberService;
    @Resource
    private MachinezoneService machinezoneService;
    @Resource
    private ChatmsgService chatmsgService;
    @Resource
    private EventService eventService;

    Logger logger = LoggerFactory.getLogger(AskForHelp.class);




    @GetMapping("/index")
    public Result index(String id){
        // 返回结果
        Map data = new HashMap();
        if(id == null || id.equals("")) return ResultGenerator.genSuccessResult(data);

        // 获取求助事件信息
        Event event = eventService.findBy("askid", id);

        // 获取求助区域，获取求助区域实时影像url
        String machine_zone = event.getLocation();
        Machinezone machinezone = machinezoneService.findBy("zoneName", machine_zone);

        // 如果列表里 有对应区域的数据，则获取机器实时影像url
        List<Map<String, String>> urls = new ArrayList<Map<String, String>>();
        if(machinezone != null){
            String machine_numbers = machinezone.getMachineNumberList();
            String[] machine_num = machine_numbers.split(",");
            for (int i=0; i<machine_num.length; i++) {
                String url;
                Machinenumber the_machine  = machinenumberService.findBy("machineNumber", machine_num[i]);
                if(the_machine != null){
                    url = the_machine.getMachineNumberUrl();
                }
                else{
                    url = "该监控设备不在已部署列表中";
                }
                Map<String, String> mp = new HashMap<String, String>();
                mp.put(machine_num[i], url);
                urls.add(mp);
            }
        }

        // 生成返回结果
        data.put("event", event); //求助人信息
        /*data.put("machine_zone_urls", urls); //实时影像信息*/

        logger.info("调用[AskForHelp/index] 单击求助电话获取页面信息，信息为为 [{}]", data.toString());

        return ResultGenerator.genSuccessResult(data);
    }

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    // 初始化求助信息：求助者信息 + 事件处理信息初始化
    /*
    * 求助者信息：姓名，身份证号
    * 求助者地理位置信息：地域信息，机器信息
    * 求助事件记录：接通，暂停，处理，处理结果，派发人员，上报，上报人员
    * */
    @GetMapping("/launch_help_init")
    public Result launch_help_init(@RequestParam Map<String, Object> helpinfo){
        Map data = new HashMap();

        // 生成uuid
        String uuid = (String) helpinfo.get("roomToken");

        // 初始化事件信息
        Event event = new Event();
        event.setAskid(uuid);
        eventService.save(event);
        Event init_result = eventService.findBy("askid", uuid);
        logger.info("调用[AskForHelp/launch_help_init] 初始化求助事件信息，求助ask_id为 [{}]", uuid);


        // 生成返回结果
        data.put("ask_id", uuid); // 求助人信息id
        data.put("event", init_result); // 事件信息id
        data.put("msg", "已初始化求助信息"); // msg

        return ResultGenerator.genSuccessResult(data);
    }

    // 更新求助信息：求助者信息，事件处理信息
    @GetMapping("update_userinfo")
    public Result update_userinfo(@RequestParam Map<String, Object> helpinfo) {
        Map data = new HashMap();

        // 生成uuid
        String uuid = (String) helpinfo.get("roomToken");

        // 获取事件信息
        Event event = eventService.findBy("askid", uuid);

        // 获取人员信息
        String identity = (String) helpinfo.get("identity");
        String location = (String) helpinfo.get("location");
        String name = (String) helpinfo.get("name");
        String sex = (String) helpinfo.get("sex");
        String machine_number = (String) helpinfo.get("machine_number");

        if (identity != null)
            event.setIdentity(identity);
        if (location != null)
            event.setIdentity(location);
        if (name != null)
            event.setIdentity(name);
        if (sex != null)
            event.setIdentity(sex);
        if (machine_number != null)
            event.setIdentity(machine_number);

        // 更新
        eventService.update(event);

        Event update_result = eventService.findBy("askid", uuid);

        data.put("event", update_result); //事件处理
        data.put("msg", "更新求助人员信息"); // 更新求助人员信息

        return ResultGenerator.genSuccessResult(data);
    }

    // 更新求助信息：求助者信息，事件处理信息
    @GetMapping("update_eventinfo")
    public Result update_eventinfo(@RequestParam Map<String, Object> helpinfo){
        Map data = new HashMap();

        // 生成uuid
        String uuid = (String) helpinfo.get("roomToken");

        // 获取事件信息
        Event event = eventService.findBy("askid", uuid);

        // 获取人员信息
        String success = (String) helpinfo.get("success");
        String pause = (String) helpinfo.get("pause");
        String handle = (String) helpinfo.get("handle");
        String username = (String) helpinfo.get("username");
        String handle_result = (String) helpinfo.get("handle_result");
        String handle_people = (String) helpinfo.get("handle_people");
        String report = (String) helpinfo.get("report");
        String report_people = (String) helpinfo.get("report_people");
        String remark = (String) helpinfo.get("remark");

        if(success != null)
            event.setIdentity(success);
        if(pause != null)
            event.setIdentity(pause);
        if(handle != null)
            event.setIdentity(handle);
        if(username != null)
            event.setIdentity(username);
        if(handle_result != null)
            event.setIdentity(handle_result);
        if(handle_people != null)
            event.setIdentity(handle_people);
        if(report != null)
            event.setIdentity(report);
        if(report_people != null)
            event.setIdentity(report_people);
        if(remark != null)
            event.setIdentity(remark);

        // 更新
        eventService.update(event);
        Event update_result = eventService.findBy("askid", uuid);

        data.put("event", update_result); //事件处理
        data.put("msg", "更新求助人员信息"); // 更新求助人员信息

        return ResultGenerator.genSuccessResult(data);
    }

    // 获取当前登录用户的用户名
    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        if (principal instanceof Principal) {
            return ((Principal) principal).getName();
        }
        return String.valueOf(principal);
    }

    // 任务派发
    @Autowired
    private DirectSender2 dsender2;
    @GetMapping("/handle_event")
    public Result handleevent(@RequestParam Map<String, Object> helpinfo){
        /*
        * helpinfo
        * @para handle_result， handle_person， handle
        * */

        // 返回结果
        Map data = new HashMap();
        // mock
        if(helpinfo.size() == 0){
            helpinfo = new HashMap();
            helpinfo.put("handle_result", "此处有乘客吸烟导致行李失火。请速去现场解决，并与中控室保持联系，感谢配合");
            helpinfo.put("handle_person", "zhangsan,lisi");
            helpinfo.put("ask_id", "1");
            helpinfo.put("handle", "1");
        }

        // 任务分配：选择列表+发送消息
        Chatmsg chatmsg = new Chatmsg();
        chatmsg.setType("task");
        chatmsg.setContent((String) helpinfo.get("handle_result"));
        chatmsg.setDate(new Date().toString());
        chatmsg.setToid((String) helpinfo.get("handle_person"));
        chatmsg.setFromid(getCurrentUsername());
        chatmsgService.save(chatmsg);
        // todo
        /*
        * 发送到rabbitmq的task queue
        * 前端实现有两种 ：
        * 1.websock发送即时消息 recive里面发websocket，chats接受，不过需要判断user，不是全部派发
        * 2.前端轮询我后端接口 poll_msg，参数userinfo：username
        *
        * 用消息队列，就是把处理接受消息的操作统一从业务代码解耦
        * */
        dsender2.sendDirect(JSON.toJSONString(chatmsg));
        /*websock发送即时消息*/
        /*ws.task();*/

        logger.info("调用[AskForHelp/handle_event] 任务分配：选择列表+发送消息，任务为 [{}]",chatmsg.toString());

        // 更新事件信息: 处理结果，处理人员， 已处理
        Result res = update_eventinfo(helpinfo);
        logger.info("调用[AskForHelp/update_eventinfo] 储存处理结果，结果为 [{}]",res.toString());


        // 生成返回结果
        data.put("update_eventinfo", res); // 更新事件信息
        data.put("send_chatmsg", chatmsg); // 更新事件信息

        return ResultGenerator.genSuccessResult(data);
    }

    // 个人消息 - 轮询接口
    @GetMapping("/poll_msg")
    public Result poll_msg(@RequestParam Map<String, Object> userinfo ) {
        String username = null;
        int page = 0;
        int size = 0;
        if(userinfo.size() == 0){
            username = "lisi";
            page = 1;
            size = 5;

        }
        Map data = new HashMap();
        List<Chatmsg> chatmsgs = null;
        if(username != null && !username.equals("")){
            Condition condition = new Condition(Chatmsg.class);
            condition.createCriteria().andLike("toid", '%'+username+'%');
            chatmsgs = chatmsgService.findByCondition(condition);

            logger.info("调用[AskForHelp/chatmsgs] 轮询取用户消息，结果为 [{}]",chatmsgs.toString());
        }

        PageHelper.startPage(page, size);

        PageInfo pageInfo = new PageInfo(chatmsgs);
        // 生成返回结果
        data.put("chatmsgs", chatmsgs); //update完的处理信息
        data.put("pageInfo", pageInfo); //update完的处理信息
        data.put("username", username); //update完的处理信息
        data.put("count", chatmsgs.size()); //update完的处理信息

        return ResultGenerator.genSuccessResult(data);
    }





}