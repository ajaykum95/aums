package com.abha.aums.subscription.models;

import com.abha.aums.shared.models.BaseEntity;
import com.abha.sharedlibrary.shared.enums.Industry;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Entity class representing an application subscriber.
 * This class is mapped to the database table {@code tbl_app_subscriber} and
 * stores subscriber-related information.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "tbl_app_subscriber")
public class AppSubscriber extends BaseEntity {
  @Column(nullable = false)
  private String firstName;
  private String lastName;
  @Column(nullable = false)
  private String email;
  private String phone;
  private String entityRef;
  private String companyName;
  private String website;
  private String companySize;
  private String salesTeamSize;
  @Enumerated(EnumType.STRING)
  private Industry industry;
}
