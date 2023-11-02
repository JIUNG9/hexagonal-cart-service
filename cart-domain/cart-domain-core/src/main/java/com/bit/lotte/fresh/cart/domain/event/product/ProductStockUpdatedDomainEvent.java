package com.bit.lotte.fresh.cart.domain.event.product;

import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import java.time.ZonedDateTime;
import lombok.Getter;

@Getter
public class ProductStockUpdatedDomainEvent extends
    ProductDomainEvent {


  public ProductStockUpdatedDomainEvent(Product product, ZonedDateTime createdTime) {
    super(product, createdTime);
  }
}
