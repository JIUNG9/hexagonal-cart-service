package com.bit.lotte.fresh.cart.domain;

import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.domain.event.product.ProductStockUpdatedDomainEvent;

public interface ProductService {
ProductStockUpdatedDomainEvent updateStock(Product product,long updatedStock);
}
