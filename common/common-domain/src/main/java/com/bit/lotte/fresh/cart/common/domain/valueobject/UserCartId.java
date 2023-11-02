package com.bit.lotte.fresh.cart.common.domain.valueobject;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCartId extends BaseId<Long>{
  @JsonCreator
  public UserCartId(@JsonProperty("userId") Long value) {
    super(value);
  }


}
