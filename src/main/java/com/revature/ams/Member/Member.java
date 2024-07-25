package com.revature.ams.Member;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false) // unique not null constraints
    private String email;
    @Column(name="member_type", columnDefinition = "varchar(15) default 'PASSENGER'")
    @Enumerated(EnumType.STRING)
    private MemberType type;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    public enum MemberType {
        PILOT, ADMIN, PASSENGER
    }
}
