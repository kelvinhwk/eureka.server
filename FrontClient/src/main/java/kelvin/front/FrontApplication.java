package kelvin.front;

import com.kelvin.Response;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import kelvin.front.feign.MyFeignClient;
import kelvin.front.feign.Service2Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
// 开启feign client
@EnableFeignClients(basePackages = "kelvin.front.feign")
@EnableCircuitBreaker
@EnableHystrixDashboard
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

    @Autowired
    MyFeignClient myFeignClient;

    @Autowired
    Service2Feign service2Feign;

    @RequestMapping("/service1")
    public Response service1() {
        return restTemplate.getForObject("http://Service-1/service", Response.class);
    }

    @RequestMapping("/service2")
    public String service2() {
        return restTemplate.getForObject("http://Service-2/service", String.class);
    }

    @RequestMapping("/feign")
    public Response feign() {
        return myFeignClient.getR();
    }

    @RequestMapping("/sleep")
    @HystrixCommand
    public Response sleep(Integer seconds) {
        return myFeignClient.sleep(seconds);
    }

    @RequestMapping("/sleep2")
    @HystrixCommand(fallbackMethod = "sleep2FallBack")
    public String sleep2(Integer seconds) {
        return service2Feign.sleep(seconds);
    }

    public String sleep2FallBack(Integer seconds) {
        return "this is fallback method :" + seconds;
    }

    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class, args);
    }
}
