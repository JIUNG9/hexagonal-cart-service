package com.bit.lotte.fresh.cart.service;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.domain.ProductService;
import com.bit.lotte.fresh.cart.domain.ProductServiceImpl;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.domain.event.product.ProductStockUpdatedDomainEvent;
import com.bit.lotte.fresh.cart.domain.excepton.CartDomainException;
import com.bit.lotte.fresh.cart.service.dto.command.UpdateCartProductStockCommand;
import com.bit.lotte.fresh.cart.service.repository.ProductRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductCommandHandler {


  private final ProductRepository productRepository;
  private final ProductService productService = new ProductServiceImpl();





  @Transactional
  public ProductStockUpdatedDomainEvent updateCartProductStock(
      UpdateCartProductStockCommand command) {
    Product foundProduct = productRepository.getProduct(new ProductId(command.getProductId()), command.getProvince());
    ProductStockUpdatedDomainEvent event =
        productService.updateStock(foundProduct, command.getUpdatedStock());
    Product updatedProduct = productRepository.updateProductStock(event.getProduct(),
        command.getUpdatedStock());
    if (updatedProduct != null) {
      return event;
    }
    throw new CartDomainException("재고를 업데이트할 수 없습니다.");
  }


}
