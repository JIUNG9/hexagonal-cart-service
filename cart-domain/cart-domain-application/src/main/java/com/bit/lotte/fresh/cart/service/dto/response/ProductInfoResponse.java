package com.bit.lotte.fresh.cart.service.dto.response;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoResponse {

  @NotNull
  private ProductId productId;
  @NotNull
  private Long stock;
  @NotNull
  private String name;
  @NotNull
  private Long price;


}
