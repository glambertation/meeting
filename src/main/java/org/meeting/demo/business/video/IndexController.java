package org.meeting.demo.business.video;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * <p>
 * IndexController
 *
 * @author 刘志强
 * @created Create Time: 2019/4/25
 */

@RestController
public class IndexController {

    @GetMapping("/")
    public void index(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.sendRedirect("/web/webBroadcast");
    }

}