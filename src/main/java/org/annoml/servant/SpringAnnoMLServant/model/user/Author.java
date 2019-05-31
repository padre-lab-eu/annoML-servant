package org.annoml.servant.SpringAnnoMLServant.model.user;

        import lombok.NonNull;
        import org.annoml.servant.SpringAnnoMLServant.model.AbstractEntity;
        import org.annoml.servant.SpringAnnoMLServant.security.SecurityConfig;

        import javax.persistence.Column;
        import javax.persistence.Entity;

/**
 * Author entity containing username and password.
 */
@Entity
public class Author extends AbstractEntity {
    @Column(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;


    public Author(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = SecurityConfig.PASSWORD_ENCODER.encode(password);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) {
            return false;
        }

        Author author = (Author) o;
        if (!this.getId().equals(author.getId())) {
            return false;
        }
        return this.getUsername().equals(author.getUsername());
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(getId());
    }

    public Author() { // jpa
    }
}