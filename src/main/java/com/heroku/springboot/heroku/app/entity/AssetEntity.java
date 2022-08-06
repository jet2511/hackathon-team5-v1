package com.heroku.springboot.heroku.app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "assets")
public class AssetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "type")
    private Long isIncome;
}
