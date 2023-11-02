package com.bit.lotte.fresh.cart.service.repository;

import com.bit.lotte.fresh.cart.common.domain.valueobject.CartItemCompKey;
import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.common.domain.valueobject.UserCartId;
import com.bit.lotte.fresh.cart.domain.entity.Cart;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository {

  Cart findCart(UserCartId id);

  CartItem addCartItem(UserCartId userCartId ,CartItem cartItem);

  CartItem findCartItem(CartItemCompKey cartItemCompKey, Province province);

  CartItem updateSelectedQuantity(CartItem cartItem);


  void removeCartItem(CartItem cartItem);



}
