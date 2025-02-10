package com.abha.aums.users.models;

import com.abha.aums.shared.models.BaseEntity;
import com.abha.aums.subscription.models.AppSubscriptions;
import com.abha.sharedlibrary.shared.enums.Gender;
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity {

    @ManyToOne(targetEntity = AppSubscriptions.class)
    @JoinColumn(name = "app_subscription_id", nullable = false)
    private AppSubscriptions appSubscriptions;

    @Column(nullable = false)
    private Integer profilePictureId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String email;

    private String phone;

    private boolean isEmailVerified;

    private boolean isMobileVerified;

    @Column(nullable = false)
    private String password;

    private String token;
}
