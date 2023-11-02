package com.bit.lotte.fresh.cart.domain.entity;

import com.bit.lotte.fresh.cart.common.domain.entity.BaseEntity;
import com.bit.lotte.fresh.cart.common.domain.valueobject.CartItemCompKey;
import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.common.domain.valueobject.UserCartId;
import com.bit.lotte.fresh.cart.domain.excepton.CartDomainException;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class CartItem extends BaseEntity<CartItemCompKey> {

  private int selectedQuantity;
  private Product product;

  public CartItem(CartItemCompKey cartItemCompKey) {
    super(cartItemCompKey);
  }


  public void removeCartItem(ProductId productId) {
    if (!product.getEntityId().equals(productId)) {
      throw new CartDomainException("카트에 존재하지 않는 상품입니다.");
    }
  }

  public void initCartItem(Product product, int selectedQuantity) {
    product.initProduct(product);
    product.isStockEnough(selectedQuantity);
    validateSelectedQuantityOver0(selectedQuantity);
  }

  public void validateSelectedQuantityOver0(int selectedQuantity) {
    if (selectedQuantity <= 0) {
      throw new CartDomainException("1개 이상을 추가해주세요.");
    }
  }

  public void updateTheSelectedQuantity(int updatedSelectedQuantity) {
    if (product.getProductStock() < updatedSelectedQuantity) {
      throw new CartDomainException("재고가 충분하지 않습니다 현재 재고는 :" + product.getProductStock());
    }
    this.selectedQuantity = updatedSelectedQuantity;
  }

}
