package com.bit.lotte.fresh.cart.app.rest;


import com.bit.lotte.fresh.cart.common.domain.valueobject.UserCartId;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import com.bit.lotte.fresh.cart.service.RemoveItemsWhichIsPaidResponse;
import com.bit.lotte.fresh.cart.service.dto.BuyProductDto;
import com.bit.lotte.fresh.cart.service.dto.command.BuyProductEmptyCommand;
import com.bit.lotte.fresh.cart.service.dto.command.BuyProductInCartCommand;
import com.bit.lotte.fresh.cart.service.dto.command.AddProductInCartCommand;
import com.bit.lotte.fresh.cart.service.dto.command.CartItemIdCommand;
import com.bit.lotte.fresh.cart.service.dto.command.GetMyAllCartItemCommand;
import com.bit.lotte.fresh.cart.service.dto.command.UpdateCartSelectedQuantityCommand;
import com.bit.lotte.fresh.cart.service.dto.response.BuyProductInCartResponse;
import com.bit.lotte.fresh.cart.service.dto.response.GetMyAllCartItemListResponse;
import com.bit.lotte.fresh.cart.service.dto.response.RemoveCartProductResponse;
import com.bit.lotte.fresh.cart.service.dto.response.UpdateCartProductSelectedQuantityResponse;
import com.bit.lotte.fresh.cart.service.port.input.CartApplicationService;
import com.bit.lotte.fresh.cart.service.util.RedisProductUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CartRestController {

  private final RedisProductUtil redisProductUtil;
  private final CartApplicationService applicationService;

  @GetMapping("/carts")
  public ResponseEntity<List<GetMyAllCartItemListResponse>> getMyAllCartItemList(
      @RequestHeader Long userId) {
    List<GetMyAllCartItemListResponse> response = applicationService.getCartItemList(
        new GetMyAllCartItemCommand(userId));
    return ResponseEntity.ok(response);
  }

  @PostMapping("/carts")
  public ResponseEntity<Object> addItemIntoCart(
      @Valid @RequestBody AddProductInCartCommand command, @RequestHeader Long userId) {
    log.info("userId:" + userId);
    log.info("product name:" + command.getProductName());
    applicationService.addProductInCart(new UserCartId(userId), command);
    return ResponseEntity.ok("상품이 추가되었습니다.");
  }

  @DeleteMapping("/carts/province/{province}/products/{productId}")
  public ResponseEntity<RemoveCartProductResponse> removeCartItem(
      @PathVariable Province province,
      @RequestHeader Long userId, @PathVariable Long productId) {
    return ResponseEntity.ok(
        applicationService.removeCartProduct(
            new CartItemIdCommand(userId, productId, province)));
  }


  @PutMapping("/carts/province/{province}/products/{productId}/stock/{selectedQuantity}")
  public ResponseEntity<UpdateCartProductSelectedQuantityResponse> updateSelectedQuantity(
      @RequestHeader Long userId, @PathVariable Long productId,
      @PathVariable Province province, @PathVariable Integer selectedQuantity) {
    return ResponseEntity.ok(applicationService.updateCartProductSelectedQuantity(
        new UpdateCartSelectedQuantityCommand(userId, productId, province, selectedQuantity)));
  }


  @DeleteMapping("/carts/products")
  public ResponseEntity<RemoveItemsWhichIsPaidResponse> removeCartItemListAfterPayment(
      @RequestBody BuyProductEmptyCommand command, @RequestHeader Long userId) {
    return ResponseEntity.ok(applicationService.removeCartProductListAfterPayment(command, userId));
  }


  @PostMapping("/carts/province/{province}/products")
  public ResponseEntity<String> buyProductInCart(
      @RequestBody List<BuyProductDto> dtoList, @RequestHeader Long userId,
      @PathVariable Province province)
      throws JsonProcessingException {
    List<BuyProductDto> buyProductDtoList = dtoList;
    redisProductUtil.saveProduct(buyProductDtoList);
    for (BuyProductDto dto : buyProductDtoList) {
      applicationService.buyProductListInCart(new CartItemIdCommand(userId,dto.getProductId(),province));
    }
    return ResponseEntity.ok("장바구니에 담긴 상품 구매가 완료되었습니다.");
  }


}
