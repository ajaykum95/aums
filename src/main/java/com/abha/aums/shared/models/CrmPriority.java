package com.abha.aums.shared.models;

import com.abha.aums.subscription.models.AppSubscriber;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_crm_priority")
public class CrmPriority extends BaseDbEntity {
  private String value;
  @ManyToMany(mappedBy = "crmPriorities")
  private Set<AppSubscriber> appSubscribers;
}
