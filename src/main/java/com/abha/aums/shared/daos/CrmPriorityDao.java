package com.abha.aums.shared.daos;

import com.abha.aums.shared.models.CrmPriority;
import java.util.List;
import java.util.Set;

public interface CrmPriorityDao {
  Set<CrmPriority> getByIdIn(List<Integer> crmPriorityIds);
}
