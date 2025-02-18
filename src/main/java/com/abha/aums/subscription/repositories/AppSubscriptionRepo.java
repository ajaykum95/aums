package com.abha.aums.subscription.repositories;

import com.abha.aums.subscription.models.AppSubscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppSubscriptionRepo extends JpaRepository<AppSubscriptions, Long> {
}
