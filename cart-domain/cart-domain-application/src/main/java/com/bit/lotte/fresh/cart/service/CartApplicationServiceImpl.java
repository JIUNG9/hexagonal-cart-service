package com.bit.lotte.fresh.cart.service;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.common.domain.valueobject.UserCartId;
import com.bit.lotte.fresh.cart.domain.entity.CartItem;
import com.bit.lotte.fresh.cart.domain.event.cart.AddCarItemCartDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.BuyCartItemDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.GetMyCartItemEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.RemoveCartItemCartDomainEvent;
import com.bit.lotte.fresh.cart.domain.event.cart.UpdateSelectedQuantityCartItemCartDomainEvent;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import com.bit.lotte.fresh.cart.service.dto.command.AddProductInCartCommand;
import com.bit.lotte.fresh.cart.service.dto.command.BuyProductEmptyCommand;
import com.bit.lotte.fresh.cart.service.dto.command.CartItemIdCommand;
import com.bit.lotte.fresh.cart.service.dto.command.GetMyAllCartItemCommand;
import com.bit.lotte.fresh.cart.service.dto.command.UpdateCartSelectedQuantityCommand;
import com.bit.lotte.fresh.cart.service.dto.response.AddProductInCartResponse;
import com.bit.lotte.fresh.cart.service.dto.response.BuyProductInCartResponse;
import com.bit.lotte.fresh.cart.service.dto.response.GetMyAllCartItemListResponse;
import com.bit.lotte.fresh.cart.service.dto.response.RemoveCartProductResponse;
import com.bit.lotte.fresh.cart.service.dto.response.UpdateCartProductSelectedQuantityResponse;
import com.bit.lotte.fresh.cart.service.mapper.CartMapper;
import com.bit.lotte.fresh.cart.service.port.input.CartApplicationService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CartApplicationServiceImpl implements
    CartApplicationService {

  private final CartCommandHandler commandHandler;
  private final CartMapper cartMapper;

  @Override
  public List<GetMyAllCartItemListResponse> getCartItemList(GetMyAllCartItemCommand command) {
    List<GetMyCartItemEvent> eventList = commandHandler.getMyAllCartItem(command);
    List<CartItem> cartItems = new ArrayList<>();

    for (GetMyCartItemEvent event : eventList) {
      cartItems.add(event.getCartItem());
    }
    return cartMapper.cartItemListToMyCartResponse(cartItems);

  }

  @Override
  public AddProductInCartResponse addProductInCart(UserCartId userCartId,
      AddProductInCartCommand command) {
    AddCarItemCartDomainEvent event = commandHandler.addCartItem(userCartId, command);
    return cartMapper.AddCartToResponse(event);
  }

  @Override
  public BuyProductInCartResponse buyProductListInCart(CartItemIdCommand command) {
    BuyCartItemDomainEvent eventList = commandHandler.buyProduct(command);

    return cartMapper.buyCartProductEventToResponse(eventList);
  }

  @Override
  public RemoveCartProductResponse removeCartProduct(CartItemIdCommand command) {
    RemoveCartItemCartDomainEvent eventList = commandHandler.removeCartItem(command);
    return cartMapper.removeCartEventToResponse(eventList);

  }

  @Override
  public RemoveItemsWhichIsPaidResponse removeCartProductListAfterPayment(
      BuyProductEmptyCommand command, Long userId) {
    Province fixedProvince = command.getProvince();
    List<Long> productIdList = command.getProductIdList();
    for (Long productId : productIdList) {
      commandHandler.removeCartItem(new CartItemIdCommand(userId, productId, fixedProvince));
    }
    return new RemoveItemsWhichIsPaidResponse();
  }

  @Override
  public UpdateCartProductSelectedQuantityResponse updateCartProductSelectedQuantity(
      UpdateCartSelectedQuantityCommand command) {
    UpdateSelectedQuantityCartItemCartDomainEvent event = commandHandler.updateCartProductSelectedQuantity(
        new UserCartId(command.getUserId()), new ProductId(command.getProductId()),
        command.getProvince(), command.getIncreasedQuantity()
    );

    return cartMapper.selectedQuantityUpdateEventToResponse(event);
  }

}
