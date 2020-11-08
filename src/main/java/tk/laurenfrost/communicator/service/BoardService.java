package tk.laurenfrost.communicator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.laurenfrost.communicator.entity.Board;
import tk.laurenfrost.communicator.repository.BoardRepository;

import java.util.UUID;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public void setBoardRepository(BoardRepository repository) {
        this.boardRepository = repository;
    }

    public Board createBoard(Board board) {
        Board oldBoard = this.boardRepository.getBoardByMacAddress(board.getMacAddress());
        if (oldBoard != null)
            return oldBoard;
        return this.boardRepository.save(board);
    }

    public void deleteBoard(UUID id) {
        clearBoardFoodList(id);
        this.boardRepository.deleteById(id);
    }

    public void clearBoardFoodList(UUID id) {
        this.boardRepository.clearBoardList(getBoardById(id));
    }

    public Board getBoardById(UUID id) {
        return this.boardRepository.getOne(id);
    }

}
