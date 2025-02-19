package com.abha.aums.shared.repositories;

import com.abha.aums.shared.models.CrmPriority;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrmPriorityRepo extends JpaRepository<CrmPriority, Long> {
  Set<CrmPriority> findByIdIn(List<Integer> crmPriorityIds);
}
