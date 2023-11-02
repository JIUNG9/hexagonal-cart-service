package com.bit.lotte.fresh.cart.app.rest;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import com.bit.lotte.fresh.cart.service.dto.command.AddProductInCartCommand;
import com.bit.lotte.fresh.cart.service.dto.command.UpdateCartProductStockCommand;
import com.bit.lotte.fresh.cart.service.dto.response.AddProductInCartResponse;
import com.bit.lotte.fresh.cart.service.dto.response.UpdateCartProductSelectedQuantityResponse;
import com.bit.lotte.fresh.cart.service.dto.response.UpdateProductStockResponse;
import com.bit.lotte.fresh.cart.service.port.input.ProductApplicationService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductRestController {

  private final ProductApplicationService productApplicationService;

  @PutMapping("/carts/products/province/{province}/products/{productId}/stock/{stockNumber}")
  public ResponseEntity<UpdateProductStockResponse> updateStock(
      @PathVariable Province province,
      @PathVariable Long productId, @PathVariable Long stockNumber) {
    return ResponseEntity.ok(productApplicationService.updateCartProductStock(
        new UpdateCartProductStockCommand(productId, province, stockNumber)));
  }
}
