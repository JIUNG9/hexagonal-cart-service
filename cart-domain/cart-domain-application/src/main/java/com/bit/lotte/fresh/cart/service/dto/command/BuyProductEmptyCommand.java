package com.bit.lotte.fresh.cart.service.dto.command;

import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuyProductEmptyCommand {
  private List<Long> productIdList;
  private Province province;
}
