package se.fabricioflores.petmarket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Objects;
import java.util.Set;
import org.hibernate.proxy.HibernateProxy;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "app_user")
@Entity
@Data
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(nullable = false, updatable = false, unique = true)
  @Setter(AccessLevel.NONE)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @JsonIgnore
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String firstname;

  @Column(nullable = false)
  private String lastname;

  @Column(nullable = false, unique = true)
  private String email;

  private String phone;

  @JsonIgnore
  @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
  private Set<Ad> ads;


  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    User user = (User) o;
    return getId() != null && Objects.equals(getId(), user.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }

}
