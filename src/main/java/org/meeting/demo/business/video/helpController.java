package org.meeting.demo.business.video;


import org.meeting.demo.model.AppUser;
import org.meeting.demo.model.Askerinfo;
import org.meeting.demo.service.AppUserService;
import org.meeting.demo.service.AskerinfoService;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * IndexController
 *
 * @author 刘志强
 * @created Create Time: 2019/4/25
 */

@RestController
@RequestMapping("/user")
public class helpController {

    @Resource
    private AskerinfoService askerinfoService;

    @Resource
    private AppUserService appUserService;


    // 发起求助
    @GetMapping("/help")
    public ModelAndView help(ModelMap modelMap) {
        Askerinfo extern_user = new Askerinfo();
        extern_user.setId(106);
        extern_user.setName("张三");
        extern_user.setSex("男");
        extern_user.setAskId("c0f71baa4f45459b818fea769c88bebc");
        extern_user.setIdentity("110111111111111");
        extern_user.setLocation("西北角");
        extern_user.setMachineId("SPT05");


        modelMap.addAttribute("extern_user", extern_user);
        return new ModelAndView("/user_broadcast", modelMap);
    }

    // 接通求助
    @GetMapping("/answer")
    public ModelAndView answer(ModelMap modelMap) {
        /*AppUser user = appUserService.findBy("username","zhangsan");*/
        String askid = "c0f71baa4f45459b818fea769c88bebc";
        Map user = new HashMap<>();
        user.put("username","zhangsan");
        user.put("askid",askid);
        modelMap.addAttribute("user", user);
        return new ModelAndView("/user_watch", modelMap);
    }

    // 多对多
    @GetMapping("/meetings")
    public ModelAndView meetings(ModelMap modelMap) {
        /*AppUser user = appUserService.findBy("username","zhangsan");*/
        String askid = "c0f71baa4f45459b818fea769c88bebc";
        Map user = new HashMap<>();
        user.put("username","zhangsan");
        user.put("askid",askid);
        modelMap.addAttribute("user", user);
        return new ModelAndView("/user_meetings", modelMap);
    }

    // 修改后的多对多
    @GetMapping("/mod")
    public ModelAndView mod(ModelMap modelMap) {
        /*AppUser user = appUserService.findBy("username","zhangsan");*/
        String askid = "c0f71baa4f45459b818fea769c88bebc";
        Map user = new HashMap<>();
        user.put("username","zhangsan");
        user.put("askid",askid);
        modelMap.addAttribute("user", user);
        System.out.println("mod");
        return new ModelAndView("/user_meetings_mod", modelMap);
    }



    /**
     * 直播页面
     * @param modelMap
     * @return
     */
    @GetMapping("webBroadcast")
    public ModelAndView webBroadcast(ModelMap modelMap){
        return new ModelAndView("/broadcast", modelMap);
    }


    /**
     * 观看页面
     * @param modelMap
     * @return
     */
    @GetMapping("webWatch")
    public ModelAndView webWatch(ModelMap modelMap){
        return new ModelAndView("/watch", modelMap);
    }

}