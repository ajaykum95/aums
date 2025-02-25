package com.abha.aums.subscription.services.impl;

import com.abha.aums.subscription.daos.SubscriptionPlanDao;
import com.abha.aums.subscription.models.PlanFeature;
import com.abha.aums.subscription.models.SubscriptionPlan;
import com.abha.aums.subscription.services.PlanFeatureService;
import com.abha.aums.subscription.services.SubscriptionPlanService;
import com.abha.aums.subscription.utils.ObjectMapperUtil;
import com.abha.sharedlibrary.aums.response.SubscriptionPlanResponse;
import com.abha.sharedlibrary.shared.enums.PlanType;
import com.abha.sharedlibrary.shared.enums.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Service implementation for managing subscription plans.
 */
@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

  private final SubscriptionPlanDao subscriptionPlanDao;
  private final PlanFeatureService planFeatureService;

  /**
   * Constructor for SubscriptionPlanServiceImpl.
   *
   * @param subscriptionPlanDao the DAO for subscription plans
   * @param planFeatureService  the service for fetching plan features
   */
  @Autowired
  public SubscriptionPlanServiceImpl(
      SubscriptionPlanDao subscriptionPlanDao, PlanFeatureService planFeatureService) {
    this.subscriptionPlanDao = subscriptionPlanDao;
    this.planFeatureService = planFeatureService;
  }

  @Override
  public List<SubscriptionPlanResponse> fetchAllActiveSubscriptionPlans() {
    List<SubscriptionPlan> subscriptionPlans =
        subscriptionPlanDao.fetchAllSubscriptionPlanByStatus(Status.ACTIVE);
    return mapToSubscriptionPlanResponse(subscriptionPlans);
  }

  @Override
  public Optional<SubscriptionPlan> fetchPlanByTypeAndStatus(PlanType planType, Status status) {
    return subscriptionPlanDao.findByPlanTypeAndStatus(planType, status);
  }

  /**
   * Fetches all active subscription plans.
   *
   * @return a list of active subscription plan responses
   */
  public List<SubscriptionPlanResponse> mapToSubscriptionPlanResponse(
      List<SubscriptionPlan> subscriptionPlans) {
    if (CollectionUtils.isEmpty(subscriptionPlans)) {
      return new ArrayList<>();
    }
    return subscriptionPlans.stream()
        .map(this::mapToSubscriptionPlanResponse)
        .collect(Collectors.toList());
  }

  /**
   * Maps a list of SubscriptionPlan entities to SubscriptionPlanResponse DTOs.
   *
   * @param subscriptionPlan the list of subscription plans
   * @return a list of subscription plan responses
   */
  private SubscriptionPlanResponse mapToSubscriptionPlanResponse(
      SubscriptionPlan subscriptionPlan) {
    List<PlanFeature> planFeatures = planFeatureService.fetchAllPlanFeatures(subscriptionPlan);
    return SubscriptionPlanResponse.builder()
        .id(subscriptionPlan.getId())
        .planType(subscriptionPlan.getPlanType())
        .planCycle(subscriptionPlan.getPlanCycle())
        .description(subscriptionPlan.getDescription())
        .price(subscriptionPlan.getPrice())
        .planFeatureResponses(ObjectMapperUtil.mapToPlanFeatureResponse(planFeatures))
        .build();
  }
}
