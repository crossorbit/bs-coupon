package cnabookstore;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long>{

    Optional<Coupon> findByCouponId(Long couponId);
    Optional<Coupon> findByOrderId(Long orderId);
}