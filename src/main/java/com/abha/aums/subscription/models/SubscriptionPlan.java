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
import lombok.experimental.SuperBuilder;

/**
 * Entity class representing a subscription plan.
 * This class is mapped to the database table {@code tbl_subscription_plan} and stores
 * details about different subscription plans, including type, price, description, and cycle.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
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
