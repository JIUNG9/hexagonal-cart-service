package com.bit.lotte.fresh.cart.message.input;

import com.bit.lotte.fresh.cart.domain.event.product.ProductStockUpdatedDomainEvent;
import com.bit.lotte.fresh.cart.service.dto.command.UpdateCartProductStockCommand;
import com.bit.lotte.fresh.cart.service.mapper.ProductMapper;
import com.bit.lotte.fresh.cart.service.port.input.ProductApplicationService;
import com.bit.lotte.fresh.cart.service.port.input.message.UpdateProductStockListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductStockUpdatedListenerImpl implements UpdateProductStockListener {

  private final ProductMapper productMapper;
  private final ProductApplicationService productService;


  @Override
  public void updateStock(ProductStockUpdatedDomainEvent event) {
    UpdateCartProductStockCommand command = productMapper.productStockUpdateEventToCommand(event);
    productService.updateCartProductStock(command);
  }
}
