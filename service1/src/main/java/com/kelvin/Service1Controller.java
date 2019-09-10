package com.kelvin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Service1Controller {

    @RequestMapping("/service")
    public Response service(HttpServletRequest req) {
        return new Response(0, "this is service1, port="
                + req.getServerPort());
    }
}
