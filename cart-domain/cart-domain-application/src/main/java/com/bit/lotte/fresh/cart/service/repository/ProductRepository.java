package com.bit.lotte.fresh.cart.service.repository;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {

  public Product save(Product product);
  public Product getProduct(ProductId productId, Province province);
  public Product updateProductStock(Product product, long stock);
}
