package com.cms.domain;

import com.cms.dto.AccountSignUpRequest;
import com.cms.type.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "accountName"),
        @UniqueConstraint(columnNames = "email")
})
@Entity
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public static Account of(AccountSignUpRequest signUpRequest, String encryptedPassword) {
        return Account.builder()
                .accountName(signUpRequest.getAccountName())
                .email(signUpRequest.getEmail())
                .password(encryptedPassword)
                .phoneNumber(signUpRequest.getPhoneNumber())
                .role(Role.USER)
                .build();
    }

}