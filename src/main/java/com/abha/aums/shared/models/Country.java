package com.abha.aums.shared.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a country.
 * This class is mapped to the database table {@code tbl_country} and
 * stores country-related information.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_country")
public class Country extends BaseEntity {

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false, unique = true)
  private String code;
}
