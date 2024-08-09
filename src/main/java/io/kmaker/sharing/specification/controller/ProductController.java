package io.kmaker.sharing.specification.controller;

import io.kmaker.sharing.specification.dto.ProductData;
import io.kmaker.sharing.specification.entity.Product;
import io.kmaker.sharing.specification.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping
    public ResponseEntity<Object> getAll(
            @RequestParam(value = "page", defaultValue = "0") final int page,
            @RequestParam(value = "limit", defaultValue = "3") final int limit
        ) {
        final var result = productRepo.findAllWithGenProj(null, PageRequest.of(page, limit), ProductData.class, Product.class);

        return ResponseEntity.ok(result);
    }
}
