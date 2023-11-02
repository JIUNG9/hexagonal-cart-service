package com.bit.lotte.fresh.cart.domain.entity;

import com.bit.lotte.fresh.cart.common.domain.entity.AggregateRoot;
import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.domain.excepton.CartDomainException;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Setter
@Getter
@AllArgsConstructor
@SuperBuilder
public class Product extends AggregateRoot<ProductId> {


  public Product() {
    super();
  }

  long discountedPrice;
  Province province;
  long productStock;
  long price;
  String name;
  String description;


  public Product(
      AggregateRootBuilder<ProductId, ?, ?> b) {
    super(b);
  }

  public Product productIdentifier(Province province, ProductId productId) {
    if (province.equals(this.province) && productId.equals(getEntityId())) {
      return this;
    }
    throw new CartDomainException("존재하는 제품이 없습니다.");
  }

  public void isStockEnough(int requestQuantity) {
    if (requestQuantity > productStock) {
      throw new CartDomainException("재고가 부족합니다.");
    }

  }

  public void initProduct(Product product) {
    validateProduct(product);
  }

  public Product updateStock(long updatedStock) {
    this.productStock = updatedStock;
    return this;
  }

  public void validateProduct(Product product) {
    validateDescription(product);
    validateName(product);
    validatePrice(product);
  }


  private void validateName(Product product) {
    if (product.name == null) {
      throw new CartDomainException("상품의 이름이 없습니다.");
    }
  }

  private void validateDescription(Product product) {
    if (product.description == null) {
      throw new CartDomainException("상품의 사진 정보가 없습니다.");
    }
  }

  private void validatePrice(Product product) {
    if (product.getPrice() <= 0) {
      throw new CartDomainException("상품의 가격은 0보다 커야합니다.");
    }
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + this.getEntityId().getValue() +
        ", originalPrice=" + productStock +
        ", discountedPrice=" + discountedPrice +
        ", productName='" + name + '\'' +
        ", productStock='" + productStock + '\'' +
        ", productThumbnail='" + description + '\'' +
        '}';
  }


}



