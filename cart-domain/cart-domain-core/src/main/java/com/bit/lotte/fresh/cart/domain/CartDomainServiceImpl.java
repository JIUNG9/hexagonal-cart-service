package com.bit.lotte.fresh.cart.domain;

import com.bit.lotte.fresh.cart.domain.entity.Cart;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.domain.event.cart.AddCarItemCartDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.BuyCartItemDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.RemoveCartItemCartDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.UpdateSelectedQuantityCartItemCartDomainEvent;
import java.time.ZonedDateTime;

public class CartDomainServiceImpl implements
    CartDomainService {

  @Override
  public AddCarItemCartDomainEvent addProductInCart(Cart cart, CartItem cartItem) {
    cart.addCartItem(cartItem);
    return new AddCarItemCartDomainEvent(cart, cartItem, ZonedDateTime.now());
  }

  @Override
  public BuyCartItemDomainEvent buyCartItem(Cart cart, CartItem cartItem) {
    cart.removeCartItem(cartItem);
    return new BuyCartItemDomainEvent(cart, cartItem, ZonedDateTime.now());
  }

  @Override
  public RemoveCartItemCartDomainEvent removeCartItem(Cart cart, CartItem cartItem) {
    cart.removeCartItem(cartItem);
    return new RemoveCartItemCartDomainEvent(cart, cartItem, ZonedDateTime.now());
  }


  @Override
  public UpdateSelectedQuantityCartItemCartDomainEvent updateSelectedProductQuantity(Cart cart,
      CartItem cartItem, int updatedSelectedQuantity) {
    cart.updateSelectedQuantity(cartItem,updatedSelectedQuantity);
    return new UpdateSelectedQuantityCartItemCartDomainEvent(cart,cartItem,ZonedDateTime.now());
  }
}
