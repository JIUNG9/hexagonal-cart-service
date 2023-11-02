package com.bit.lotte.fresh.cart.service.dto.command;

import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductIdentifier {

  private Long productId;
  private Province province;
}
