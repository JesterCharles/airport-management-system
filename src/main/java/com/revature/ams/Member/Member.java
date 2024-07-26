package com.revature.ams.Member;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// LOMBOK
@Data
@NoArgsConstructor
@AllArgsConstructor
// JPA
@Entity
@Table(name="members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    @NotNull
    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false) // unique not null constraints
    @NotNull
    @Email
    private String email;

    @Column(name="member_type", columnDefinition = "varchar(15) default 'PASSENGER'", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType type;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "Password must match minimum eight characters, at least one letter, one number and one special character") // Minimum eight characters, at least one letter, one number and one special character:
    private String password;

    public enum MemberType {
        PILOT, ADMIN, PASSENGER
    }
}
