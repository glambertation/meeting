package org.meeting.demo.business.video;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>
 * IndexController
 *
 * @author 刘志强
 * @created Create Time: 2019/4/25
 */

//@RestController
@Controller
@RequestMapping("/web")
public class mainController {

    /**
     * 直播页面
     * @param model
     * @return
     */
    @RequestMapping("/webBroadcast")
    public ModelAndView webBroadcast(Model model){
        ModelAndView mv = new ModelAndView("broadcast");
        System.out.println("webBroadcast");
        return mv;
    }


    /**
     * 观看页面
     * @param model
     * @return
     */
    @RequestMapping("/webWatch")
    public ModelAndView webWatch(Model model){
        ModelAndView mv = new ModelAndView("watch");
        return mv;
    }

}