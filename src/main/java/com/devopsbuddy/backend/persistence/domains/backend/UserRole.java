package com.devopsbuddy.backend.persistence.domains.backend;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Jnwanya on
 * Wed, 27 Dec, 2017.
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable{

    /** The serialversionUID for a serializabled class **/
    private static final long serialVersionUID = 1L;

    public UserRole(){

    }

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(user, userRole.user) &&
                Objects.equals(role, userRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, role);
    }
}
