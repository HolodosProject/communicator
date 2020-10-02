package tk.laurenfrost.communicator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tk.laurenfrost.communicator.entity.Board;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {
    @Transactional
    @Modifying
    @Query(value = "delete from Food where board = :board")
    void clearBoardList(Board board);

    Board getBoardByMacAddress(String macAddress);
}
