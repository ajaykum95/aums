package com.abha.aums.shared.daos.impl;

import com.abha.aums.shared.daos.CrmPriorityDao;
import com.abha.aums.shared.models.CrmPriority;
import com.abha.aums.shared.repositories.CrmPriorityRepo;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Repository
public class CrmPriorityDaoImpl implements CrmPriorityDao {

  private final CrmPriorityRepo crmPriorityRepo;

  public CrmPriorityDaoImpl(CrmPriorityRepo crmPriorityRepo) {
    this.crmPriorityRepo = crmPriorityRepo;
  }

  @Override
  public Set<CrmPriority> getByIdIn(List<Integer> crmPriorityIds) {
    return crmPriorityRepo.findByIdIn(crmPriorityIds);
  }
}
