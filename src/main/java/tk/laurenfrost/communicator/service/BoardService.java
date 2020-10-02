package tk.laurenfrost.communicator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.laurenfrost.communicator.entity.Board;
import tk.laurenfrost.communicator.entity.Food;
import tk.laurenfrost.communicator.repository.BoardRepository;
import tk.laurenfrost.communicator.repository.FoodRepository;

import java.util.UUID;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    private FoodRepository foodRepository;

    @Autowired
    public void setBoardRepository(BoardRepository repository) {
        this.boardRepository = repository;
    }

    @Autowired
    public void setFoodRepository(FoodRepository repository) {
        this.foodRepository = repository;
    }

    public UUID createBoard(String id) {
        Board board = this.boardRepository.getBoardByMacAddress(id);
        if (board != null)
            return board.getId();
        board = new Board();
        board.setMacAddress(id);
        this.boardRepository.save(board);
        return board.getId();
    }

    public void deleteBoard(UUID id) {
        clearBoardFoodList(id);
        this.boardRepository.deleteById(id);
    }

    public void clearBoardFoodList(UUID id) {
        this.boardRepository.clearBoardList(getBoardById(id));
    }

    public UUID addFood(UUID boardId, String foodName) {
        Board board = this.boardRepository.getOne(boardId);
        for (Food food : board.getFoodList()) {
            if (food.getName().equals(foodName)) {
                food.setQuantity(food.getQuantity() + 1);
                foodRepository.save(food);
                return food.getId();
            }
        }
        Food newFood = new Food();
        newFood.setName(foodName);
        newFood.setQuantity(1);
        newFood.setBoard(board);
        board.getFoodList().add(newFood);
        foodRepository.save(newFood);
        return newFood.getId();
    }

    public Board getBoardById(UUID id) {
        return this.boardRepository.getOne(id);
    }

}
