package com.heroku.springboot.heroku.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "finance_target_category")
@Entity
@Data
public class FinanceTargetCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private boolean status;

    @Column(name = "note")
    private String note;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "financeTargetCategory", cascade = CascadeType.ALL)
    private List<FinanceTargetDetailEntity> financeTargetDetails;
}
