package com.bit.lotte.fresh.cart.common.domain.valueobject;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductId extends BaseId<Long> {
  @JsonCreator
  public ProductId(@JsonProperty("productId")Long value) {
    super(value);
  }



}
