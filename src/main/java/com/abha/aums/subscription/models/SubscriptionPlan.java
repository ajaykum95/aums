package com.abha.aums.subscription.models;

import com.abha.aums.shared.models.BaseEntity;
import com.abha.sharedlibrary.shared.enums.PlanCycle;
import com.abha.sharedlibrary.shared.enums.PlanType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_subscription_plan")
public class SubscriptionPlan extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlanType planType;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlanCycle planCycle;
}
