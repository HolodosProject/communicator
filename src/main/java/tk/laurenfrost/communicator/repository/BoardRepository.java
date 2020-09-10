package tk.laurenfrost.communicator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.laurenfrost.communicator.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
}
