package tk.laurenfrost.communicator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.laurenfrost.communicator.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
