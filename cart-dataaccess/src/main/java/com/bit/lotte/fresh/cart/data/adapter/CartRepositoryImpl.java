package com.bit.lotte.fresh.cart.data.adapter;

import com.bit.lotte.fresh.cart.common.domain.valueobject.CartItemCompKey;
import com.bit.lotte.fresh.cart.common.domain.valueobject.UserCartId;
import com.bit.lotte.fresh.cart.data.entity.CartItemEntity;
import com.bit.lotte.fresh.cart.data.entity.ProductEntity;
import com.bit.lotte.fresh.cart.data.mapper.ProductDataMapper;
import com.bit.lotte.fresh.cart.data.repository.CartItemJpaRepository;
import com.bit.lotte.fresh.cart.data.valueobject.CartItemEntityKey;
import com.bit.lotte.fresh.cart.domain.entity.Cart;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.excepton.CartDomainException;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import com.bit.lotte.fresh.cart.service.mapper.ProductMapper;
import com.bit.lotte.fresh.cart.service.repository.CartRepository;
import com.bit.lotte.fresh.cart.data.entity.CartEntity;
import com.bit.lotte.fresh.cart.data.mapper.CartDataMapper;
import com.bit.lotte.fresh.cart.data.repository.CartJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CartRepositoryImpl implements CartRepository {

  private final ProductDataMapper productDataMapper;
  private final CartItemJpaRepository cartItemJpaRepository;
  private final CartJpaRepository cartJpaRepository;
  private final CartDataMapper cartDataMapper;


  private CartItemEntityKey keyConvertor(CartItemCompKey cartItemCompKey) {
    return new CartItemEntityKey(cartItemCompKey.getUserCartId().getValue(),
        cartItemCompKey.getProductId().getValue());

  }

  @Override
  public Cart findCart(UserCartId id) {
    Optional<CartEntity> entityOptional = cartJpaRepository.findById(id.getValue());
    if (entityOptional.isEmpty()) {
      return null;
    }
    return cartDataMapper.cartEntityToCart(entityOptional.get());
  }

  @Override
  public CartItem addCartItem(UserCartId userCartId, CartItem cartItem) {
    Cart cart = findCart(userCartId);
    if (cart == null) {
      log.info("cart is null");
      CartEntity cartEntity = new CartEntity();
      cartEntity.setCartUserId(userCartId.getValue());
      cartJpaRepository.save(cartEntity);
    }
    log.info("now cart is not null");
      CartEntity cartEntity = new CartEntity();
      CartItemEntity cartItemEntity = new CartItemEntity();
      cartEntity.setCartUserId(userCartId.getValue());
      cartItemEntity.setCartEntity(cartEntity);
      cartItemEntity.setSelectedQuantity(cartItem.getSelectedQuantity());
      cartItemEntity.setId(keyConvertor(cartItem.getEntityId()));
      cartItemEntity.setProduct(productDataMapper.productToProductEntity(cartItem.getProduct()));
    CartItemEntity savedCartItem = cartItemJpaRepository.save(cartItemEntity);
    return cartDataMapper.cartItemEntityToCartItem(savedCartItem);

  }

  @Override
  public CartItem findCartItem(CartItemCompKey cartItemCompKey, Province province) {
    Optional<CartItemEntity> entityOptional = cartItemJpaRepository.findByProductProvinceAndId(
        province, keyConvertor(cartItemCompKey));
    return entityOptional.map(cartDataMapper::cartItemEntityToCartItem).orElse(null);
  }

  @Override
  public CartItem updateSelectedQuantity(CartItem cartItem) {
    Optional<CartItemEntity> cartItemEntityOptional = cartItemJpaRepository.findById(
        keyConvertor(cartItem.getEntityId()));
    if (cartItemEntityOptional.isEmpty()) {
      throw new CartDomainException("카트에 해당 제품이 존재하지 않습니다.");
    }
    CartItemEntity cartItemEntity = cartItemEntityOptional.get();
    cartItemEntity.setSelectedQuantity(cartItem.getSelectedQuantity());
    cartItemJpaRepository.save(cartItemEntity);
    return cartDataMapper.cartItemEntityToCartItem(
        cartItemJpaRepository.save(cartItemEntityOptional.get()));
  }


  @Override
  public void removeCartItem(CartItem cartItem) {
    cartItemJpaRepository.deleteById(keyConvertor(cartItem.getEntityId()));
  }


}
