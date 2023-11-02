package com.bit.lotte.fresh.cart.service.dto.command;

import com.bit.lotte.fresh.cart.common.domain.valueobject.UserCartId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetMyAllCartItemCommand {
  private  Long userCartId;
}
