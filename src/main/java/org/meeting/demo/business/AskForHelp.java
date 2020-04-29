package org.meeting.demo.business;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.meeting.demo.core.Result;
import org.meeting.demo.core.ResultGenerator;
import org.meeting.demo.model.*;
import org.meeting.demo.rabbitmq.direct.DirectSender2;
/*import org.meeting.demo.rabbitmq.model.ChatMsg;*/
import java.security.Principal;

/*import org.meeting.demo.rabbitmq.model.ChatMsg;*/
import org.meeting.demo.service.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import org.slf4j.Logger;
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
    private UsersService usersService;
    @Resource
    private AskerinfoService askerinfoService;
    @Resource
    private AskqueueService askqueueService;
    @Resource
    private EventhandleService eventhandleService;
    @Resource
    private MachinenumberService machinenumberService;
    @Resource
    private MachinezoneService machinezoneService;
    @Resource
    private ChatmsgService chatmsgService;

    Logger logger = LoggerFactory.getLogger(AskForHelp.class);




    @GetMapping("/index")
    public Result index(String id){
        // 参数id 是视频列表里面的id

        // 返回结果
        Map data = new HashMap();

        // 获取求助队列
        /*List<Askqueue> ask_queue = askqueueService.findAll();*/
        // 获取待处理的视频
        Condition condition = new Condition(Askqueue.class);
        condition.createCriteria().andEqualTo("handle","0");
        List<Askqueue> ask_queue = askqueueService.findByCondition(condition);

        // 没有求助视频直接返回
        if(ask_queue.size()==0){
            return ResultGenerator.genSuccessResult("没有求助视频");
        }

        // 如果没有选择视频，则选择视频队列里的首位求助视频
        if (id == null || id.length() == 0) {
            id = ask_queue.get(0).getAskId();
        }

        //id类型转换
        int ask_id = Integer.valueOf(id).intValue();

        // 获取求助人信息
        Askerinfo asker_info = askerinfoService.findById(ask_id);

        // 获取求助区域，获取求助区域实时影像url
        String machine_zone = asker_info.getLocation();
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

        // 获取事件处理结果
        Eventhandle event_handle = eventhandleService.findBy("askId",id);

        // 生成返回结果
        data.put("asker_info", asker_info); //求助人信息
        data.put("Ask_queue", ask_queue); //求助队列
        data.put("machine_zone_urls", urls); //实时影像信息
        data.put("event_handle", event_handle); // 事件处理结果

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
    @GetMapping("/launch_help_init")
    public Result insert_vedio_queue(@RequestParam Map<String, Object> helpinfo){
        Map data = new HashMap();

        // 生成uuid
        /*String uuid = getUUID();*/
        String uuid = (String) helpinfo.get("roomToken");

        // 获取求助人信息
        Askerinfo asker_info = new Askerinfo();
        asker_info.setIdentity((String) helpinfo.get("identity"));
        asker_info.setLocation((String) helpinfo.get("location"));
        asker_info.setName((String) helpinfo.get("name"));
        asker_info.setSex((String) helpinfo.get("sex"));
        asker_info.setMachineId((String) helpinfo.get("machine_id"));
        asker_info.setVideoUrl((String) helpinfo.get("video_url"));
        asker_info.setAskId(uuid);

        askerinfoService.save(asker_info);
        Askerinfo asker_info_result = askerinfoService.findBy("askId", uuid);
        logger.info("调用[AskForHelp/launch_help_init] 初始化求助人信息，求助ask_id为 [{}]", uuid);

        // 初始化对应事件信息
        Eventhandle event_handle = new Eventhandle();
        event_handle.setAskId(uuid);
        event_handle.setEventHandleId(uuid);

        eventhandleService.save(event_handle);
        Eventhandle event_handle_result = eventhandleService.findBy("eventHandleId", uuid);
        logger.info("调用[AskForHelp/launch_help_init] 初始化对应事件信息，求助ask_id为 [{}]", uuid);

        // 加入到求助队列里
        Askqueue ask_queue = new Askqueue();
        ask_queue.setAskId(uuid);
        ask_queue.setHandle("0");
        ask_queue.setMachineNumber((String) helpinfo.get("machine_number"));
        ask_queue.setMachineZone((String) helpinfo.get("machine_zone"));
        ask_queue.setPause("0");
        ask_queue.setSuccess("0"); //是否接通
        ask_queue.setTime(new Date().toString());
        ask_queue.setVideoUrl((String) helpinfo.get("video_url"));

        askqueueService.save(ask_queue);
        Askqueue ask_queue_result = askqueueService.findBy("askId", uuid);
        logger.info("调用[AskForHelp/launch_help_init] 初始化加入到求助队列里，结果为 [{}]", ask_queue_result.toString());


        // 生成返回结果
        data.put("ask_id", uuid); //求助人信息id
        data.put("asker_info_result", asker_info_result); //求助人信息id
        data.put("event_handle_result", event_handle_result); //事件处理
        data.put("ask_queue_result", ask_queue_result); //求助队列
        data.put("msg", "已初始化求助信息"); //实时影像信息

        return ResultGenerator.genSuccessResult(data);
    }

    // 轮询接口
    @GetMapping("/polling")
    public Result poll_queue(){
        Map data = new HashMap();

        // 获取待处理求助队列
        Condition condition = new Condition(Askqueue.class);
        condition.createCriteria().andEqualTo("handle","0");
        List<Askqueue> ask_queue = askqueueService.findByCondition(condition);
        logger.info("调用[AskForHelp/polling] 获取待处理求助队列，求助队列为 [{}]",ask_queue.toString());

        // 生成返回结果
        data.put("Ask_queue", ask_queue); //求助队列

        return ResultGenerator.genSuccessResult(data);
    }

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

    @Autowired
    private DirectSender2 dsender2;
    @GetMapping("/handle_event")
    public Result handleevent(@RequestParam Map<String, Object> helpinfo){

        System.out.println("helpinfo");
        System.out.println(helpinfo);
        if(helpinfo.size() == 0){
            helpinfo = new HashMap();
            helpinfo.put("handle_result", "此处有乘客吸烟导致行李失火。请速去现场解决，并与中控室保持联系，感谢配合");
            helpinfo.put("handle_person", "zhangsan,lisi");
            helpinfo.put("ask_id", "1");
            helpinfo.put("handle", "1");

        }

        System.out.println("helpinfo");
        System.out.println(JSON.toJSONString(helpinfo));
        Map data = new HashMap();

        // 任务分配：选择列表+发送消息
        Chatmsg chatmsg = new Chatmsg();
        chatmsg.setType("task");
        chatmsg.setContent((String) helpinfo.get("handle_result"));
        chatmsg.setDate(new Date().toString());
        chatmsg.setToid((String) helpinfo.get("handle_person"));
        chatmsg.setFromid(getCurrentUsername());

        chatmsgService.save(chatmsg);
        /*Map task = new HashMap();
        task.put("handle_result", (String) helpinfo.get("handle_result"));
        task.put("handle_person", (String) helpinfo.get("handle_person"));
        task.put("type", "send_task");*/
        // todo
        dsender2.sendDirect(JSON.toJSONString(chatmsg));
        /*websock发送即时消息*/
        /*ws.task();*/

        logger.info("调用[AskForHelp/handle_event] 任务分配：选择列表+发送消息，任务为 [{}]",chatmsg.toString());

        // 存储处理结果+存储处理人员+标注该求助已处理
        Eventhandle event_handle = new Eventhandle();
        event_handle.setAskId((String) helpinfo.get("ask_id"));
        event_handle.setHandle((String) helpinfo.get("handle"));
        event_handle.setHandlePerson((String) helpinfo.get("handle_person"));
        event_handle.setHandleResult((String) helpinfo.get("handle_result"));

        eventhandleService.update(event_handle);
        Eventhandle get_event_handle = eventhandleService.findBy("askId", (String) helpinfo.get("ask_id"));
        logger.info("调用[AskForHelp/handle_event] 储存处理结果，结果为 [{}]",get_event_handle.toString());

        // 获取待处理队列
        // 如果已经处理了，就不能上报了？
        Condition condition = new Condition(Askqueue.class);
        condition.createCriteria().andEqualTo("handle","0");
        List<Askqueue> ask_queue = askqueueService.findByCondition(condition);
        logger.info("调用[AskForHelp/handle_event] 刷新待处理队列，结果为 [{}]",ask_queue.toString());



        // 生成返回结果
        data.put("update_event_handle", get_event_handle); //update完的处理信息
        data.put("ask_queue", ask_queue); //求助队列

        return ResultGenerator.genSuccessResult(data);
    }

    // 事件处理 - 事件上报
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

    // 事件处理 - 事件上报
    @GetMapping("/event_report")
    public Result event_report(@RequestParam Map<String, Object> helpinfo) {
        Map data = new HashMap();

        // 事件上报
        Eventhandle event_handle = new Eventhandle();
        event_handle.setAskId((String) helpinfo.get("ask_id"));
        event_handle.setReport("1");
        event_handle.setReportPerson((String) helpinfo.get("report_person"));

        eventhandleService.update(event_handle);
        Eventhandle get_event_handle = eventhandleService.findBy("askId", (String) helpinfo.get("ask_id"));
        logger.info("调用[AskForHelp/event_report] 事件上报结果，结果为 [{}]",get_event_handle.toString());

        // 生成返回结果
        data.put("get_event_handle", get_event_handle); //update完的处理信息

        return ResultGenerator.genSuccessResult(data);
    }

    @PostMapping("/add")
    public Result add(Users users) {
    usersService.save(users);
    return ResultGenerator.genSuccessResult();
    }


        @GetMapping("/adds")
        public Result adds() {
            Users user = new Users();
            user.setUsername("kk");
            user.setPassword("123");
            user.setNickName("11");
            user.setRegisterDate(new Date());
            user.setSex(1);
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