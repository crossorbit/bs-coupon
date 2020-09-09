package cnabookstore;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Coupon_table")
public class Coupon {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long couponId;
    private Long customerId;
    private String couponStatus;
    private Long orderId;

    @PostPersist
    public void onPostPersist(){
        System.out.println("##### onPostPersist start ##### ");
        CouponCreated couponCreated = new CouponCreated();
        BeanUtils.copyProperties(this, couponCreated);
        couponCreated.publishAfterCommit();
        System.out.println("##### onPostPersist end ##### ");
    }

    @PostUpdate
    public void onPostUpdate(){
        System.out.println("##### onPostUpdate start ##### ");
        CouponUsed couponUsed = new CouponUsed();
        BeanUtils.copyProperties(this, couponUsed);
        couponUsed.publishAfterCommit();
        System.out.println("##### onPostUpdate end ##### ");
    }

    @PostRemove
    public void onPrePersist(){
        CouponRefund couponRefund = new CouponRefund();
        BeanUtils.copyProperties(this, couponRefund);
        couponRefund.publishAfterCommit();
    }


    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }




}
