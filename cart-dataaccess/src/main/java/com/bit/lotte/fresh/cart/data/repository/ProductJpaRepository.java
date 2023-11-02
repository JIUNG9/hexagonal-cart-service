package com.bit.lotte.fresh.cart.data.repository;

import com.bit.lotte.fresh.cart.data.entity.CartItemEntity;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import com.bit.lotte.fresh.cart.data.entity.ProductEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
  public Optional<ProductEntity> findByProductIdAndProvince(Long productId, Province province);
}
