package com.bit.lotte.fresh.cart.service;

import com.bit.lotte.fresh.cart.common.domain.valueobject.CartItemCompKey;
import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.common.domain.valueobject.UserCartId;
import com.bit.lotte.fresh.cart.domain.CartDomainService;
import com.bit.lotte.fresh.cart.domain.CartDomainServiceImpl;
import com.bit.lotte.fresh.cart.domain.entity.Cart;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.event.cart.AddCarItemCartDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.BuyCartItemDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.GetMyCartItemEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.RemoveCartItemCartDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.UpdateSelectedQuantityCartItemCartDomainEvent;
import com.bit.lotte.fresh.cart.domain.excepton.CartDomainException;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import com.bit.lotte.fresh.cart.service.dto.command.AddProductInCartCommand;
import com.bit.lotte.fresh.cart.service.dto.command.CartItemIdCommand;
import com.bit.lotte.fresh.cart.service.dto.command.GetMyAllCartItemCommand;
import com.bit.lotte.fresh.cart.service.mapper.CartMapper;
import com.bit.lotte.fresh.cart.service.repository.CartRepository;
import com.bit.lotte.fresh.cart.service.util.RedisProductUtil;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CartCommandHandler {
  private final CartDomainService cartDomainService = new CartDomainServiceImpl();
  private final CartRepository cartRepository;
  private final CartMapper cartMapper;

  private Cart findCart(UserCartId cartId) {
    return cartRepository.findCart(cartId);
  }

  private CartItem findCartItem(CartItemIdCommand command) {
    return cartRepository.findCartItem(
        new CartItemCompKey(new ProductId(command.getProductId()), new UserCartId(command.getUserCartId())),
        command.getProvince());
  }

  private Cart cartInitHelper(UserCartId userCartId) {
    Cart foundCart = findCart(userCartId);
    if (foundCart == null) {
      return Cart.builder().id(userCartId).cartItemList(null).build();
    }
    return foundCart;
  }

  private void cartItemExistChecker(CartItemIdCommand command) {
    CartItem foundCartItem = findCartItem(command);

    if (foundCartItem == null) {
      throw new CartDomainException("존재하지 않는 카트 상품입니다.");
    }
  }


  @Transactional
  public AddCarItemCartDomainEvent addCartItem(UserCartId userCartId,
      AddProductInCartCommand command) {
    Cart initCart = cartInitHelper(userCartId);
    CartItem cartItem = cartMapper.getCartItemFromAddProductInCartCommand(userCartId,command);
    AddCarItemCartDomainEvent event = cartDomainService.addProductInCart(initCart, cartItem);
    CartItem savedCartItem = cartRepository.addCartItem(event.getCart().getEntityId(),
        event.getCartItem());
    if (savedCartItem != null) {
      return event;
    }
    throw new CartDomainException("상품을 추가할 수 없습니다.");
  }


  @Transactional
  public BuyCartItemDomainEvent buyProduct(CartItemIdCommand command) {
    cartItemExistChecker(command);
    Cart cart = findCart(new UserCartId(command.getUserCartId()));
    CartItem cartItem = findCartItem(command);


    BuyCartItemDomainEvent event = cartDomainService.buyCartItem(cart, cartItem);
    return event;
  }

  @Transactional
  public RemoveCartItemCartDomainEvent removeCartItem(CartItemIdCommand command) {

    cartItemExistChecker(command);
    Cart cart = findCart(new UserCartId(command.getUserCartId()));
    CartItem cartItem = findCartItem(command);

    RemoveCartItemCartDomainEvent event = cartDomainService.removeCartItem(cart, cartItem);

    cartRepository.removeCartItem(event.getCartItem());


    return event;
  }


  @Transactional
  public UpdateSelectedQuantityCartItemCartDomainEvent updateCartProductSelectedQuantity(
      UserCartId userId,ProductId productId, Province province, int updatedQuantity) {
    log.info("updatedQuantity:" +  updatedQuantity);
    CartItemIdCommand cartItemIdCommand = new CartItemIdCommand(userId.getValue(), productId.getValue(),province);
    cartItemExistChecker(cartItemIdCommand);
    Cart cart = findCart(new UserCartId(cartItemIdCommand.getUserCartId()));
    CartItem cartItem = findCartItem(cartItemIdCommand);

    UpdateSelectedQuantityCartItemCartDomainEvent event = cartDomainService.updateSelectedProductQuantity(
        cart, cartItem, updatedQuantity);
    CartItem updatedCartItem = cartRepository.updateSelectedQuantity(event.getCartItem());
    if (updatedCartItem != null) {
      return event;
    }
    throw new CartDomainException("선택한 개수만큼 업데이트를 반영할 수 없습니다.");
  }

  public GetMyCartItemEvent getMyCartItem(GetMyAllCartItemCommand command, CartItem cartItem) {
    Cart cart = findCart(new UserCartId(command.getUserCartId()));
    if (cart == null) {
      throw new CartDomainException("존재 하지 않는 카트 상품입니다.");
    } else {
      return new GetMyCartItemEvent(cart, cartItem, ZonedDateTime.now());
    }

  }

  public List<GetMyCartItemEvent> getMyAllCartItem(GetMyAllCartItemCommand command) {

    Cart cart = findCart(new UserCartId(command.getUserCartId()));
    if (cart == null) {
      return null;
    } else {
      List<GetMyCartItemEvent> eventList = new ArrayList<>();
      for (CartItem cartItem : cart.getCartItemList()) {
        eventList.add(new GetMyCartItemEvent(cart, cartItem, ZonedDateTime.now()));
      }
      return eventList;
    }


  }
}
