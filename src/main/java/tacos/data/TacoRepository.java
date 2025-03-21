package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TacoRepository extends JpaRepository<Taco, Long> {
}