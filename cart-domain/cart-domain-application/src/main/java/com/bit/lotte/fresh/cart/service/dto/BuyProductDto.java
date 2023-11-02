package com.bit.lotte.fresh.cart.service.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

@NotNull
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuyProductDto {

  @NotNull
  private Long productId;
  @NotNull
  private Long originalPrice;
  private Long discountPrice;
  @NotNull
  private Long productStock;
  @NotNull
  private String productName;
  @NotNull
  private String productThumbnail;

  public String returnJsonValue() throws JsonProcessingException {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(this);
    return json;
  }


}
