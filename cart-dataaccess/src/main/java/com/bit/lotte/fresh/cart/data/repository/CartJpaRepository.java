package com.bit.lotte.fresh.cart.data.repository;

import com.bit.lotte.fresh.cart.data.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity,Long> {

}
