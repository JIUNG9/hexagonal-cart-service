package com.bit.lotte.fresh.cart.domain;

import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.domain.event.product.ProductStockUpdatedDomainEvent;
import java.time.ZonedDateTime;

public class ProductServiceImpl implements ProductService {

  @Override
  public ProductStockUpdatedDomainEvent updateStock(Product product, long updatedStock) {
    product.updateStock(updatedStock);
    return new ProductStockUpdatedDomainEvent(product, ZonedDateTime.now());
  }
}
