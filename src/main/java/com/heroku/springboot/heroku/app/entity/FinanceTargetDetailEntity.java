package com.heroku.springboot.heroku.app.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "finance_target_detail")
@Entity
@Data
public class FinanceTargetDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "note")
    private String note;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "finance_category_id")
    private FinanceTargetCategoryEntity financeTargetCategory;
}
