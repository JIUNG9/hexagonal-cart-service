package com.bit.lotte.fresh.cart.common.domain.entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuperBuilder
public abstract class AggregateRoot<ID> extends BaseEntity<ID> {

  public AggregateRoot(ID id) {
    super(id);
    log.info(id.toString());
  }

  public AggregateRoot() {
  }

  public AggregateRoot(
      BaseEntityBuilder<ID, ?, ?> b) {
    super(b);
  }
}
