package com.bit.lotte.fresh.cart.data.valueobject;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
public class CartItemEntityKey implements Serializable {
    private Long cartEntityId;
    private Long productId;
}
