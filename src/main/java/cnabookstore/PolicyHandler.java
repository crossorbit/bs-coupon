package cnabookstore;

import cnabookstore.CouponRepository;
import cnabookstore.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    CouponRepository couponRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_UseCoupon(@Payload Ordered ordered){

        if(ordered.isMe()){
            System.out.println("##### listener ChangeCoupon : " + ordered.toJson());

            Optional<Coupon> couponOptional = couponRepository.findByCouponId(ordered.getCouponId());
            Coupon coupon = couponOptional.get();
            coupon.setOrderId(ordered.getOrderId());
            coupon.setCouponStatus("Used(Not Available)");

            couponRepository.save(coupon);

        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_ChangeCoupon(@Payload OrderCanceled orderCanceled){

        if(orderCanceled.isMe()){
            System.out.println("##### listener ChangeCoupon : " + orderCanceled.toJson());

            Optional<Coupon> couponOptional = couponRepository.findByOrderId(orderCanceled.getOrderId());
            Coupon coupon = couponOptional.get();
            coupon.setCouponStatus("Refunded(Available)");

            couponRepository.save(coupon);

        }
    }


}
