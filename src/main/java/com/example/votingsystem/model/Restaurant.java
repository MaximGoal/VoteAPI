package com.example.votingsystem.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@NoArgsConstructor
public class Restaurant extends BaseEntity {

    private Map<LocalDateTime, Menu> menu;


}
