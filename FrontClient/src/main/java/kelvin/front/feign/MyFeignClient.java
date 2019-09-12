package kelvin.front.feign;

import com.kelvin.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("Service-1")
public interface MyFeignClient {

    @RequestMapping(value = "/service", method = RequestMethod.GET)
    Response getR();

    @RequestMapping(value = "/sleep?sleep={seconds}")
    Response sleep(@PathVariable("seconds") Integer seconds);
}
