package tk.laurenfrost.communicator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.laurenfrost.communicator.entity.Board;
import tk.laurenfrost.communicator.entity.Food;
import tk.laurenfrost.communicator.repository.BoardRepository;

import java.util.Iterator;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public void setBoardRepository(BoardRepository repository) {
        this.boardRepository = repository;
    }

    public void createBoard(String id) {
        Board board = new Board();
        board.setMacAddress(id);
        this.boardRepository.save(board);
    }

    public void deleteBoard(String id) {
        this.boardRepository.deleteById(id);
    }

    public void clearBoardFoodList(String id) {
        Board board = this.boardRepository.getOne(id);
        board.getFoodList().clear();
        this.boardRepository.save(board);
    }

    public void addFood(String boardId, String foodName) {
        Board board = this.boardRepository.getOne(boardId);
        boolean exists = false;
        for (Food food : board.getFoodList()) {
            if (food.getName().equals(foodName)) {
                food.setQuantity(food.getQuantity()+1);
                exists = true;
                break;
            }
        }
        if (!exists) {
            Food newFood = new Food();
            newFood.setName(foodName);
            newFood.setQuantity(1);
            board.getFoodList().add(newFood);
        }
        this.boardRepository.save(board);
    }

    public Board getBoardById(String id) {
        return this.boardRepository.getOne(id);
    }

    public void deleteAllFoodByName(String boardId, String foodName) {
        Board board =this.boardRepository.getOne(boardId);
        board.getFoodList().removeIf(food -> food.getName().equals(foodName));
        this.boardRepository.save(board);
    }

}
