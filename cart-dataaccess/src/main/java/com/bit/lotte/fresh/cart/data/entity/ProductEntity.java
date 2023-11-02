package com.bit.lotte.fresh.cart.data.entity;

import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductEntity {

  @Id
  @Column(name = "product_id")
  private Long productId;
  @NotNull
  private String name;
  @NotNull
  private long price;
  @NotNull
  private long discountedPrice;
  @NotNull
  private String description;
  @NotNull
  private long stock;
  @NotNull
  Province province;

}
