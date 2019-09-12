package com.kelvin.service2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Service2Controller {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/service")
    public String service() {
        String result = restTemplate.getForObject("http://Service-1/service", String.class);
        return result += " 'this is service2 add'";
    }

    @RequestMapping("/sleep")
    public String sleep(HttpServletRequest req, Integer sleep) {

        if (sleep == null) {
            sleep = 1000;
        }

        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "sleeped " + sleep + " second!" + req.getServerPort();
    }
}
