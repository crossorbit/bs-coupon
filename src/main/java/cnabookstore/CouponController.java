package cnabookstore;


<<<<<<< HEAD
import java.util.Optional;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

=======
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
>>>>>>> 4b53c4e5cb57578a9f88784c80fdc57470f5cc3a

 @RestController
 public class CouponController {

<<<<<<< HEAD
  @Autowired
  CouponRepository couponRepository;

  @GetMapping("/circuitBreaker")
  @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
//          @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
//          @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
//          @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
  })
  public String circuitBreakerTest(@RequestParam String isYn) throws InterruptedException {

   if (isYn.equals("Y")) {
    System.out.println("### Circuit Breaker ###");
    Thread.sleep(10000);
   }

   System.out.println("### Coupon Success ###");
   return "Coupon Success";
  }

  @GetMapping("/selectDeliveryInfo")
  @HystrixCommand(fallbackMethod = "fallbackDelivery", commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
  })
  public String selectCouponInfo(@RequestParam long couponId) throws InterruptedException {

   if (couponId <= 0) {
    System.out.println("### Circuit Breaker ###");
    Thread.sleep(10000);
    //throw new RuntimeException("CircuitBreaker!!!");
   } else {
    Optional<Coupon> coupon = couponRepository.findByCouponId(couponId);
    return coupon.get().getCouponStatus();
   }

   System.out.println("### Coupon Success ###");
   return "Coupon Success";
  }

  private String fallback(String isYn) {
   System.out.println("### fallback ###");
   return "Circuit Breaker";
=======
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
>>>>>>> 4b53c4e5cb57578a9f88784c80fdc57470f5cc3a
  }

  private String fallbackDelivery(long deliveryId) {
   System.out.println("### fallback!!!");
   return "CircuitBreaker!!!";
  }
 }
