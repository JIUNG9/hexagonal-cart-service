package com.bit.lotte.fresh.cart.service.dto.command;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.common.domain.valueobject.UserCartId;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartProductStockCommand {

  @NotNull
  private Long productId;
  @NotNull
  private Province province;
  @NotNull
  private long updatedStock;

}
