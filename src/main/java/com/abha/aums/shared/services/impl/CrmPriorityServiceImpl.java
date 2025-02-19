package com.abha.aums.shared.services.impl;

import com.abha.aums.shared.daos.CrmPriorityDao;
import com.abha.aums.shared.models.CrmPriority;
import com.abha.aums.shared.services.CrmPriorityService;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class CrmPriorityServiceImpl implements CrmPriorityService {

  private final CrmPriorityDao crmPriorityDao;

  public CrmPriorityServiceImpl(CrmPriorityDao crmPriorityDao) {
    this.crmPriorityDao = crmPriorityDao;
  }

  @Override
  public Set<CrmPriority> findByIdIn(List<Integer> crmPriorityIds) {
    return crmPriorityDao.getByIdIn(crmPriorityIds);
  }
}
