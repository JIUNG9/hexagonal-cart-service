package com.bit.lotte.fresh.cart.data.adapter;

import com.bit.lotte.fresh.cart.common.domain.valueobject.ProductId;
import com.bit.lotte.fresh.cart.domain.entity.Product;
import com.bit.lotte.fresh.cart.domain.excepton.CartDomainException;
import com.bit.lotte.fresh.cart.domain.valueobject.Province;
import com.bit.lotte.fresh.cart.service.repository.ProductRepository;
import com.bit.lotte.fresh.cart.data.entity.ProductEntity;
import com.bit.lotte.fresh.cart.data.mapper.ProductDataMapper;
import com.bit.lotte.fresh.cart.data.repository.ProductJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductJpaRepository productJpaRepository;
  private final ProductDataMapper productDataMapper;

  @Override
  public Product save(Product product) {
    productJpaRepository.save(productDataMapper.productToProductEntity(product));
    return product;
  }

  @Override
  public Product getProduct(ProductId productId, Province province) {
    Optional<ProductEntity> optionalProductEntity = productJpaRepository.findByProductIdAndProvince(
        productId.getValue(), province);
    return optionalProductEntity.map(productDataMapper::productEntityToProduct).orElse(null);
  }

  @Override
  public Product updateProductStock(Product product, long stock) {
    product.updateStock(stock);
    productJpaRepository.save(productDataMapper.productToProductEntity(product));
    return  product;
  }


}
