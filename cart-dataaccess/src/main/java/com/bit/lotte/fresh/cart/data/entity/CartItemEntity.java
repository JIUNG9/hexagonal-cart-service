package com.bit.lotte.fresh.cart.data.entity;

import com.bit.lotte.fresh.cart.data.valueobject.CartItemEntityKey;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyToOne;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Table(name = "cart_item_entity")
public class CartItemEntity {

    @EmbeddedId
    private CartItemEntityKey id;

    @MapsId("cartEntityId")
    @ManyToOne
    @JoinColumn(name = "cart_entity_cart_user_id")
    private CartEntity cartEntity;

    @MapsId("productId")
    @OneToOne(cascade = {javax.persistence.CascadeType.PERSIST,
        javax.persistence.CascadeType.MERGE})
    private ProductEntity product;
    private int selectedQuantity;

}