package com.bit.lotte.fresh.cart.service.dto.command;

import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateCartSelectedQuantityCommand {

  @NotNull
  private Long userId;
  @NotNull
  private Long productId;
  @NotNull
  private Province province;
  @NotNull
  private int increasedQuantity;

}
