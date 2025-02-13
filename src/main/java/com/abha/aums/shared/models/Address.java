package com.abha.aums.shared.models;

import com.abha.sharedlibrary.shared.enums.AddressOwner;
import com.abha.sharedlibrary.shared.enums.AddressType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing an address.
 * This class is mapped to the database table {@code tbl_address} and stores details
 * about different addresses.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_address")
public class Address extends BaseEntity {

  @Column(nullable = false)
  private Long parentId;

  @Column(nullable = false)
  private boolean isDefault;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private AddressType addressType;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private AddressOwner addressOwner;

  @Column(nullable = false, columnDefinition = "TEXT", name = "address_line_1")
  private String addressLine1;

  @Column(columnDefinition = "TEXT", name = "address_line_2")
  private String addressLine2;

  @ManyToOne(targetEntity = Country.class)
  @JoinColumn(name = "country_id", nullable = false)
  private Country country;

  @ManyToOne(targetEntity = State.class)
  @JoinColumn(name = "state_id", nullable = false)
  private State state;

  @Column(nullable = false)
  private String cityName;

  @Column(nullable = false)
  private String pinCode;
}
