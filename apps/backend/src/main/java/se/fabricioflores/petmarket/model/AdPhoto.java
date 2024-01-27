package se.fabricioflores.petmarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static jakarta.persistence.GenerationType.IDENTITY;
import java.util.Objects;
import org.hibernate.proxy.HibernateProxy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.AccessLevel;

@Entity
@Data
@NoArgsConstructor
public class AdPhoto {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(nullable = false, updatable = false, unique = true)
  @Setter(AccessLevel.NONE)
  private Long id;

  @Column(nullable = false, updatable = false)
  private String url;

  @JsonIgnore
  @ManyToOne
  private Ad ad;


  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    AdPhoto adPhoto = (AdPhoto) o;
    return getId() != null && Objects.equals(getId(), adPhoto.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }

}
