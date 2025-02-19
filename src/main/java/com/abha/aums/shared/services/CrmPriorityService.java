package com.abha.aums.shared.services;

import com.abha.aums.shared.models.CrmPriority;
import java.util.List;
import java.util.Set;

public interface CrmPriorityService {
  Set<CrmPriority> findByIdIn(List<Integer> crmPriorityIds);
}
