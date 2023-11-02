package com.bit.lotte.fresh.cart.service.port.input;

import com.bit.lotte.fresh.cart.service.dto.command.UpdateCartProductStockCommand;
import com.bit.lotte.fresh.cart.service.dto.response.UpdateProductStockResponse;

public interface ProductApplicationService {
  UpdateProductStockResponse updateCartProductStock(UpdateCartProductStockCommand command);

}
