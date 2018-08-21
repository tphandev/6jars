package com.petproject.jars.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category extends AuditModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3,max=100)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private TransactionType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
