package com.abha.aums.shared.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_state")
public class State extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String shortName;

    @Column(nullable = false)
    private String stateCode;

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
}
