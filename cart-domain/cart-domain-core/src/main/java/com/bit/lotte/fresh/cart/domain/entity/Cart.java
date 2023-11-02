package com.bit.lotte.fresh.cart.domain.entity;

import com.bit.lotte.fresh.cart.common.domain.entity.AggregateRoot;

import com.bit.lotte.fresh.cart.common.domain.valueobject.UserCartId;
import com.bit.lotte.fresh.cart.domain.excepton.CartDomainException;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Product의 stock의 업데이트는 해당 Aggregatea Root에서 다루지 않는다. 재품의 재고의 동기화를 위한 entity이므로 해당 Aggregate Root에서
 * 다루지 않는다.
 */
@Data
@SuperBuilder
public class Cart extends AggregateRoot<UserCartId> {

  private List<CartItem> cartItemList;

  public Cart(
      AggregateRootBuilder<UserCartId, ?, ?> b) {
    super(b);
  }

  private CartItem getCartItem(CartItem cartItem) {
    for (CartItem item : cartItemList) {
      if (item.getProduct().getName().equals(cartItem.getProduct().getName()))
        return item;
      }

    throw new CartDomainException("카트에 존재하지 않는 상품입니다.");
  }

  public void updateSelectedQuantity(CartItem cartItem, int updatedQuantity) {
    cartItem.updateTheSelectedQuantity(updatedQuantity);
  }
  private boolean isItemAlreadyExist(CartItem cartItem) {
    for(CartItem cartItemIterator: cartItemList){
      if(cartItemIterator.getProduct().getName().equals(cartItem.getProduct().getName())) return true;
    }
    return false;
  }

  public void addCartItem(CartItem cartItem) {
    Product product = cartItem.getProduct();
    product.initProduct(product);
    if (cartItemList==null) {
      cartItemList= new ArrayList<>();
      addNewCartItem(cartItem);
    }
    if (isItemAlreadyExist(cartItem)) {
      addCartItemAlreadyExist(cartItem);
    } else {
      addNewCartItem(cartItem);
    }
  }
  private void addCartItemAlreadyExist(CartItem cartItem) {
    cartItem.updateTheSelectedQuantity(getCartItem(cartItem).getSelectedQuantity() +cartItem.getSelectedQuantity());
  }

  private void addNewCartItem(CartItem cartItem) {
    cartItem.initCartItem(cartItem.getProduct(),cartItem.getSelectedQuantity());
    cartItemList.add(cartItem);
  }

  public void removeCartItem(CartItem cartItem) {
    cartItem.removeCartItem(cartItem.getProduct().getEntityId());
    cartItemList.remove(cartItem);
  }


  public void buyCartItem(CartItem cartItem) {
    removeCartItem(cartItem);
  }
}


