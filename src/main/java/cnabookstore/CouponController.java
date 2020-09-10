package cnabookstore;

import org.springframework.web.bind.annotation.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class CouponController {

    @GetMapping("/selectCouponInfo")
    @HystrixCommand(fallbackMethod = "fallbackCoupon", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
    })
    public String selectCouponInfo(@RequestParam long couponId) throws InterruptedException {
        
        if (couponId == 0) {
            System.out.println("@@@ CircuitBreaker");
            Thread.sleep(10000);
            return "CircuitBreaker";
        } else {
            System.out.println("@@@ CouponInfo OK");
            return "CouponInfo_Completed";
        }
    }

    public String fallbackCoupon(long couponId ){
        System.out.println("### fallback!!!");
        return "CircuitBreaker";
    }
 }