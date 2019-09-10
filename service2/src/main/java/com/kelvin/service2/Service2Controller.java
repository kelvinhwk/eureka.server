package com.kelvin.service2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Service2Controller {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/service")
    public String service() {
        String result = restTemplate.getForObject("http://Service-1/service", String.class);
        return result += " 'this is service2 add'";
    }
}
