package com.bit.lotte.fresh.cart.service.dto.command;

import com.bit.lotte.fresh.cart.service.dto.BuyProductDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BuyProductInCartCommand {
  List<BuyProductDto> buyProductDtoList;

}
