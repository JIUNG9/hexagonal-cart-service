package com.bit.lotte.fresh.cart.data.repository;

import com.bit.lotte.fresh.cart.data.entity.CartItemEntity;
import com.bit.lotte.fresh.cart.data.valueobject.CartItemEntityKey;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, CartItemEntityKey> {
  Optional<CartItemEntity> findByProductProvinceAndId(Province province, CartItemEntityKey id);
}
