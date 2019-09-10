package kelvin.front;

import com.kelvin.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class FrontApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/front")
    public String front() {
        return "front";
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/service1")
    public Response service1() {
        return restTemplate.getForObject("http://Service-1/service", Response.class);
    }

    @RequestMapping("/service2")
    public String service2() {
        return restTemplate.getForObject("http://Service-2/service", String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class, args);
    }
}
