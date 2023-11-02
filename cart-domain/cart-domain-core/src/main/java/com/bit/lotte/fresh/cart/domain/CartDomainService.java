package com.bit.lotte.fresh.cart.domain;

import com.bit.lotte.fresh.cart.domain.entity.Cart;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.domain.event.cart.AddCarItemCartDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.BuyCartItemDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.RemoveCartItemCartDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.UpdateSelectedQuantityCartItemCartDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.product.ProductStockUpdatedDomainEvent;

public interface CartDomainService {

  AddCarItemCartDomainEvent addProductInCart(Cart cart, CartItem cartItem);

  BuyCartItemDomainEvent buyCartItem(Cart cart,CartItem cartItem);

  RemoveCartItemCartDomainEvent removeCartItem(Cart cart,CartItem cartItem);

  UpdateSelectedQuantityCartItemCartDomainEvent updateSelectedProductQuantity(Cart cart, CartItem cartItem, int increasedSelectedQuantity);


}
