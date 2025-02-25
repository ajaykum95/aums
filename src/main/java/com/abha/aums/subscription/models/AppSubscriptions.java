package com.abha.aums.subscription.models;

import com.abha.aums.shared.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Entity class representing an application subscription.
 * This class is mapped to the database table {@code tbl_app_subscriptions} and stores details
 * about subscriptions associated with application subscribers.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "tbl_app_subscriptions")
public class AppSubscriptions extends BaseEntity {

  @ManyToOne(targetEntity = AppSubscriber.class)
  @JoinColumn(name = "app_subscriber_id", nullable = false)
  private AppSubscriber appSubscriber;

  @ManyToOne(targetEntity = SubscriptionPlan.class)
  @JoinColumn(name = "subscription_plan_id", nullable = false)
  private SubscriptionPlan subscriptionPlan;

  @Column(nullable = false)
  private ZonedDateTime startDateTime;

  @Column(nullable = false)
  private ZonedDateTime endDateTime;
}
