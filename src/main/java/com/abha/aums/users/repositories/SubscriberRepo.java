package com.abha.aums.users.repositories;

import com.abha.aums.subscription.models.AppSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepo extends JpaRepository<AppSubscriber, Long> {
}
