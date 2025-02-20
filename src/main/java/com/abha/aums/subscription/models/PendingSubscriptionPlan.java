package com.abha.aums.subscription.models;

import com.abha.sharedlibrary.shared.enums.ActivityStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Builder
@Entity
@Table(name = "tbl_pending_subs_plan")
public class PendingSubscriptionPlan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private Date createdAt;

  @Column(nullable = false)
  private String createdBy;

  @ManyToOne(targetEntity = AppSubscriber.class)
  @JoinColumn(name = "app_subscriber_id", nullable = false)
  private AppSubscriber appSubscriber;

  @ManyToOne(targetEntity = SubscriptionPlan.class)
  @JoinColumn(name = "subscription_plan_id", nullable = false)
  private SubscriptionPlan subscriptionPlan;

  @Column(nullable = false)
  private Long paymentEntityId;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ActivityStatus activityStatus;
}
