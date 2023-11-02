package com.bit.lotte.fresh.cart.service.port.input.message;

import com.bit.lotte.fresh.cart.domain.event.product.ProductStockUpdatedDomainEvent;
import com.bit.lotte.fresh.cart.service.dto.response.UpdateProductStockResponse;

public interface UpdateProductStockListener {
    void updateStock(ProductStockUpdatedDomainEvent event);

}
