package com.bit.lotte.fresh.cart.service.dto.command;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.common.domain.valueobject.UserCartId;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AddProductInCartCommand {

  @NotNull
  Long productId;
  @NotNull
  long discountedPrice;
  @NotNull
  Province province;
  @NotNull
  long productStock;
  @NotNull
  long price;
  @NotNull
  String productName;
  @NotNull
  String productImageUrl;
  @NotNull
  private int selectedQuantity;

  @JsonCreator
  public AddProductInCartCommand(
      @JsonProperty("productId") Long productId, long discountedPrice,
      Province province, long productStock, long price, String productName,
      String productImageUrl, int selectedQuantity) {
    this.productId = productId;
    this.discountedPrice = discountedPrice;
    this.province = province;
    this.productStock = productStock;
    this.price = price;
    this.productName = productName;
    this.productImageUrl = productImageUrl;
    this.selectedQuantity = selectedQuantity;
  }
}
