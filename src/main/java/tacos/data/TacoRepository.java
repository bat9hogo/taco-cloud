package tacos.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import tacos.Taco;


public interface TacoRepository extends JpaRepository<Taco, Long> {
}