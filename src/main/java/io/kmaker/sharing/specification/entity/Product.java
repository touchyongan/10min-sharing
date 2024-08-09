package io.kmaker.sharing.specification.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;

@Table(name = "products")
@Entity
@Getter
@Setter
public class Product {

    @Id
    @Column(name = "id")
    @Generated
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
