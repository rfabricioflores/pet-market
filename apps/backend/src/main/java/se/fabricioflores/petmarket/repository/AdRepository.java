package se.fabricioflores.petmarket.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import se.fabricioflores.petmarket.model.Ad;

public interface AdRepository extends PagingAndSortingRepository<Ad, Long> {
}
