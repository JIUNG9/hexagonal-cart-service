package com.bit.lotte.fresh.cart.data.mapper;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.data.entity.ProductEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProductDataMapper {

  public List<Product> getProductListFromProductEntityList(List<ProductEntity> entityList) {
    List<Product> productList = new ArrayList<>();
    for (ProductEntity productEntity : entityList) {
      productList.add(productEntityToProduct(productEntity));
    }
    return productList;
  }

  public ProductEntity productToProductEntity(Product product) {
    return ProductEntity.builder().description(product.getDescription()).name(
            product.getName()).stock(product.getProductStock()).province(product.getProvince()).discountedPrice(product.getDiscountedPrice())
        .price(product.getPrice()).productId(product.getId().getValue())

        .build();
  }

  public Product productEntityToProduct(ProductEntity productEntity) {
    return Product.builder().description(productEntity.getDescription()).name(
            productEntity.getName()).productStock(productEntity.getStock()).province(productEntity.getProvince())
        .price(productEntity.getPrice()).id(new ProductId(productEntity.getProductId())).discountedPrice(productEntity.getDiscountedPrice())
        .build();
  }

  public List<ProductEntity> productToProductEntity(List<Product> productList) {
    List<ProductEntity> entityList = new ArrayList<>();
    for (Product p : productList) {
      entityList.add(
          ProductEntity.builder().name(p.getName()).price(p.getPrice()).province(p.getProvince())
              .description(p.getDescription()).productId(p.getEntityId().getValue())
              .stock(p.getProductStock()).build());
    }
    return entityList;
  }
}
