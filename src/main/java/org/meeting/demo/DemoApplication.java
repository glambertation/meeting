package org.meeting.demo;

import org.meeting.demo.business.PushRTMP;
import org.meeting.demo.business.PushRTMPS;
import org.meeting.demo.business.PushRTMPSS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);
/*        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PushRTMPS.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PushRTMPSS.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
    }

}


