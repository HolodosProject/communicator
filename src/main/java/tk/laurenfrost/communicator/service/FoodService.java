package tk.laurenfrost.communicator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.laurenfrost.communicator.entity.Board;
import tk.laurenfrost.communicator.entity.Food;
import tk.laurenfrost.communicator.repository.FoodRepository;

import java.util.UUID;

@Service
public class FoodService {

    private FoodRepository foodRepository;

    @Autowired
    public void setFoodRepository(FoodRepository repository) {
        this.foodRepository = repository;
    }

    public void deleteFood(UUID food) {
        this.foodRepository.deleteById(food);
    }

    public Food saveFood(Food food) {
        if (food.getBoard() == null) {
            Board board = this.foodRepository.getOne(food.getId()).getBoard();
            food.setBoard(board);
        }
        this.foodRepository.save(food);
        return food;
    }

    public Food createFood(Food food) {
        this.foodRepository.save(food);
        return food;
    }

}
