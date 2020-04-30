package org.meeting.demo.business;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class helpController {


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

    // 修改后的多对多
    @GetMapping("/one")
    public ModelAndView one(ModelMap modelMap) {
        /*AppUser user = appUserService.findBy("username","zhangsan");*/
        String askid = "c0f71baa4f45459b818fea769c88bebc";
        Map user = new HashMap<>();
        user.put("username","zhangsan");
        user.put("askid",askid);
        modelMap.addAttribute("user", user);
        System.out.println("mod");
        return new ModelAndView("/user_meetings_one", modelMap);
    }

    // 修改后的多对多
    @GetMapping("/onehelp")
    public ModelAndView onehelp(ModelMap modelMap) {
        /*AppUser user = appUserService.findBy("username","zhangsan");*/
        String askid = "c0f71baa4f45459b818fea769c88bebc";
        Map user = new HashMap<>();
        user.put("username","zhangsan");
        user.put("askid",askid);
        modelMap.addAttribute("user", user);
        System.out.println("mod");
        return new ModelAndView("/user_meetings_onehelp", modelMap);
    }

    // chat
    @GetMapping("/chat")
    public ModelAndView chatpage(ModelMap modelMap) {
        /*AppUser user = appUserService.findBy("username","zhangsan");*/
        String askid = "c0f71baa4f45459b818fea769c88bebc";
        Map user = new HashMap<>();
        user.put("username","zhangsan");
        user.put("askid",askid);
        modelMap.addAttribute("user", user);
        System.out.println("mod");
        return new ModelAndView("/chat", modelMap);
    }

    // chat
    @GetMapping("/chats")
    public ModelAndView chatspage(ModelMap modelMap) {
        /*AppUser user = appUserService.findBy("username","zhangsan");*/
        String askid = "c0f71baa4f45459b818fea769c88bebc";
        Map user = new HashMap<>();
        user.put("username","zhangsan");
        user.put("askid",askid);
        modelMap.addAttribute("user", user);
        System.out.println("mod");
        return new ModelAndView("/chats", modelMap);
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