package cnabookstore;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

 @RestController
 public class couponController {

  @GetMapping("/selectCouponInfo")
  @HystrixCommand(fallbackMethod = "fallbackCoupon", commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
  })
  public @ResponseBody String selectCouponInfo(@RequestParam long couponId) throws InterruptedException {
    
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
