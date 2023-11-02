package com.bit.lotte.fresh.cart.service.mapper;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.domain.event.product.ProductStockUpdatedDomainEvent;
import com.bit.lotte.fresh.cart.service.dto.command.UpdateCartProductStockCommand;
import com.bit.lotte.fresh.cart.service.dto.response.UpdateProductStockResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public UpdateProductStockResponse updateProductStockEventToResponse(
      ProductStockUpdatedDomainEvent event) {
    return new UpdateProductStockResponse(event.getProduct().getEntityId(),
        event.getProduct().getProductStock(), event.getProduct().getProvince());
  }


  public UpdateCartProductStockCommand productStockUpdateEventToCommand(
      ProductStockUpdatedDomainEvent event) {
    Product updatedStockProduct = event.getProduct();
    return new UpdateCartProductStockCommand(
        updatedStockProduct.getEntityId().getValue(), updatedStockProduct.getProvince(),
        updatedStockProduct.getProductStock());

  }
}
