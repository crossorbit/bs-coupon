package cnabookstore;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;


 @RestController
 public class CouponController {

  boolean flag;

  public VanController(){
   flag = true;
  }

  @GetMapping("/selectCouponInfo")
  @HystrixCommand(fallbackMethod = "fallbackCoupon", commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
  })
  public @ResponseBody String selectCouponInfo(@RequestParam long couponId) throws InterruptedException {
    System.out.println("@@@ requestPayment!!!");
    if (couponId == 0) {
      System.out.println("@@@ CircuitBreaker!!!");
      Thread.sleep(10000);
     return "CouponInfo_Failed";
    } else {
      System.out.println("@@@ Success!!!");
      return "CouponInfo_Completed";
    }
  }

  public String fallbackCoupon(long couponId ){
    System.out.println("### fallback!!!");
    return "fallbackCoupon";
  }

  @GetMapping("/isHealthy")
  public void test() throws Exception {
   if (flag) {
    System.out.println("health.... !!!");
   }
   else{
    throw new Exception("Zombie...");
   }
  }

  @GetMapping("/makeZombie")
  public void zombie(){
   flag = false;
  }

 }
