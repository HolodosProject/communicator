package tk.laurenfrost.communicator.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.laurenfrost.communicator.entity.Board;
import tk.laurenfrost.communicator.entity.Food;
import tk.laurenfrost.communicator.repository.FoodRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final Logger logger = LoggerFactory.getLogger(FoodService.class);

    private final FoodRepository foodRepository;

    public void deleteFood(UUID food) {
        this.foodRepository.deleteById(food);
    }

    public Food saveFood(Food food) {
        if (food.getBoard() == null) {
            Board board = this.foodRepository.getOne(food.getId()).getBoard();
            food.setBoard(board);
        }
        return this.foodRepository.save(food);
    }

    public Food createFood(Food food) {
        Food savedFood;
        Optional<Food> foodInList =
                food.getBoard().getFoodList().stream().filter(food1 -> food1.getName().equals(food.getName()))
                        .findFirst();
        if (foodInList.isEmpty()) {
            this.foodRepository.save(food);
            logger.debug("Added " + food.getName() + " to " + food.getBoard().getMacAddress() + "'s list");
            savedFood = food;
        } else {
            logger.debug(food.getName() + " is already present in " + food.getBoard().getMacAddress() + "'s list");
            savedFood = foodInList.get();
        }
        return savedFood;
    }

}
