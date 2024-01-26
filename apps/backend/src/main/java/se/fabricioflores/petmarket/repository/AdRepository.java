package se.fabricioflores.petmarket.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import se.fabricioflores.petmarket.model.Ad;
import se.fabricioflores.petmarket.projection.AdProjection;
import se.fabricioflores.petmarket.projection.AdWithUserInfoProjection;
import java.util.Optional;

public interface AdRepository extends PagingAndSortingRepository<Ad, Long>, CrudRepository<Ad, Long>{
  Optional<AdProjection> findOneById(Long id);
  Optional<AdWithUserInfoProjection> findOneWithUserById(Long id);
  Page<AdProjection> findAllBy(Pageable pageable);
  Page<AdProjection> findAllByTitleContaining(String title, Pageable pageable);
}
