package com.bit.lotte.fresh.cart.service.util;

import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.service.dto.BuyProductDto;
import com.bit.lotte.fresh.cart.service.dto.command.BuyProductInCartCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
public class RedisProductUtil {

  private final RedisTemplate redisTemplate;

  public void saveProduct(List<BuyProductDto> dtoList) throws JsonProcessingException {
    log.info("redis product:{} ", dtoList);
    for (BuyProductDto dto : dtoList) {
      redisTemplate.opsForValue().set("cart_" + dto.getProductId(), dto.returnJsonValue());
    }
  }

}
