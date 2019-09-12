package kelvin.front.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("Service-2")
public interface Service2Feign {

    @RequestMapping("/sleep?sleep={seconds}")
    String sleep(@PathVariable("seconds") Integer seconds);
}
