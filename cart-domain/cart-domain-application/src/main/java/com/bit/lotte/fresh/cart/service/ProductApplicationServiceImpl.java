package com.bit.lotte.fresh.cart.service;

import com.bit.lotte.fresh.cart.domain.event.product.ProductStockUpdatedDomainEvent;
import com.bit.lotte.fresh.cart.service.dto.command.UpdateCartProductStockCommand;
import com.bit.lotte.fresh.cart.service.dto.response.UpdateProductStockResponse;
import com.bit.lotte.fresh.cart.service.mapper.ProductMapper;
import com.bit.lotte.fresh.cart.service.port.input.ProductApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class ProductApplicationServiceImpl implements ProductApplicationService {

  private final ProductMapper productMapper;
  private final ProductCommandHandler commandHandler;
  @Override
  public UpdateProductStockResponse updateCartProductStock(UpdateCartProductStockCommand command) {
    ProductStockUpdatedDomainEvent event = commandHandler.updateCartProductStock(command);
    return productMapper.updateProductStockEventToResponse(event);
  }
}
