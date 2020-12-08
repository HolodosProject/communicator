package tk.laurenfrost.communicator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tk.laurenfrost.communicator.entity.Board;
import tk.laurenfrost.communicator.entity.Food;

import java.util.UUID;

@Repository
public interface FoodRepository extends JpaRepository<Food, UUID> {

    @Query("select f from Food f where f.name = :name and f.board = :board")
    Food getByNameAndBoard(@Param("name") String name, @Param("board") Board board);

}
