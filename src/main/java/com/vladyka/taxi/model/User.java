package com.vladyka.taxi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name")
    @Length(min = 5, message = "{lang.min_length_username}")
    @NotEmpty(message = "{lang.username_non_empty}")
    private String userName;

    @Column(name = "password")
    @Length(min = 5, message = "{lang.min_length_password}")
    @NotEmpty(message = "{lang.password_non_empty}")
    private String password;

    @Column(name = "phone_number")
    @Pattern(regexp="(^$|[0-9]{10})", message = "{lang.phone_validation}")
    @NotEmpty(message = "{lang.phone_non_empty}")
    private String phoneNumber;

    @Column(name = "active")
    private boolean isActive;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(name = "user_fund")
    private BigDecimal fund;

    @OneToMany(mappedBy="fkUser",cascade=CascadeType.ALL,fetch=FetchType.LAZY,
            orphanRemoval=true)
    private List<Order> orders;

    public void addRole(Role role) {
        roles.add(role);
    }
}
