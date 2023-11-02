package com.bit.lotte.fresh.cart.data.mapper;

import com.bit.lotte.fresh.cart.common.domain.valueobject.CartItemCompKey;
import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.common.domain.valueobject.UserCartId;
import com.bit.lotte.fresh.cart.data.entity.CartItemEntity;
import com.bit.lotte.fresh.cart.data.valueobject.CartItemEntityKey;
import com.bit.lotte.fresh.cart.domain.entity.Cart;
import com.bit.lotte.fresh.cart.data.entity.CartEntity;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CartDataMapper {

  private final ProductDataMapper productDataMapper;

  private Product getProductFromCartItem(CartItem cartItem) {
    return cartItem.getProduct();
  }

  public Cart cartEntityToCart(CartEntity cartEntity) {
    return Cart.builder().id(new UserCartId(cartEntity.getCartUserId()))
        .cartItemList(cartEntityListToCartList(cartEntity.getCartItemEntityList())).build();
  }

  public List<CartItem> cartEntityListToCartList(List<CartItemEntity> list){
    List<CartItem> cartItemList = new ArrayList<>();
    for(CartItemEntity entity : list){
      cartItemList.add(cartItemEntityToCartItem(entity));
    }
    return cartItemList;
  }


  public CartItem cartItemEntityToCartItem(CartItemEntity savedCartItem) {
    CartItemEntityKey entityCompKey= savedCartItem.getId();
    ProductId productId = new ProductId(entityCompKey.getProductId());
    UserCartId userCartId = new UserCartId(entityCompKey.getCartEntityId());
    return CartItem.builder().selectedQuantity(savedCartItem.getSelectedQuantity())
        .product(productDataMapper.productEntityToProduct(savedCartItem.getProduct()))
        .id(new CartItemCompKey(productId,userCartId)).build();
  }

}
