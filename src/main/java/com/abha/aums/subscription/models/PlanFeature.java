package com.abha.aums.subscription.models;

import com.abha.aums.shared.models.BaseEntity;
import com.abha.sharedlibrary.shared.enums.FeatureType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Entity class representing a feature associated with a subscription plan.
 * This class is mapped to the database table {@code tbl_plan_feature} and stores details
 * about the features available in different subscription plans.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "tbl_plan_feature")
public class PlanFeature extends BaseEntity {

  @ManyToOne(targetEntity = SubscriptionPlan.class)
  @JoinColumn(name = "subscription_plan_id", nullable = false)
  private SubscriptionPlan subscriptionPlan;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private FeatureType featureType;

  @Min(value = 1, message = "Minimum 1 feature value required!")
  private int featureValue;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String featureDescription;
}
