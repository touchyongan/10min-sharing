package io.kmaker.sharing.specification.repo;

import io.kmaker.sharing.specification.entity.Product;
import io.kmaker.sharing.specification.generic.demo.DemoBaseGenericProjection;

public interface ProductRepo extends BaseJpaRepository<Product>, DemoBaseGenericProjection<Product> {
}
