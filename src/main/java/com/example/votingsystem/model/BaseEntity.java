package com.example.votingsystem.model;

import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    protected String name;

    public BaseEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        Assert.notNull(id, "ID must not be null");
        return id;
    }

    public boolean isNew() {
        return this.id == null;
    }
}
