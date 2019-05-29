package org.annoml.servant.SpringAnnoMLServant.model;

        import lombok.NonNull;
        import org.hibernate.validator.constraints.NotBlank;
        import org.springframework.security.access.SecurityConfig;

        import javax.persistence.Column;
        import javax.persistence.Entity;

/**
 * Account entity containing username and password.
 */
@Entity
public class User extends AbstractEntity {
    @Column(unique = true)
    @NonNull
    private String username;


    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;
        if (!this.getId().equals(user.getId())) {
            return false;
        }
        return this.getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(getId());
    }

    public User() { // jpa
    }
}