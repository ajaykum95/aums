package com.abha.aums.subscription.models;

import com.abha.aums.shared.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing an application subscriber.
 * This class is mapped to the database table {@code tbl_app_subscriber} and
 * stores subscriber-related information.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_app_subscriber")
public class AppSubscriber extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  private String phone;
}
