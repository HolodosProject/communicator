package tk.laurenfrost.communicator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.laurenfrost.communicator.entity.Food;
import tk.laurenfrost.communicator.repository.BoardRepository;
import tk.laurenfrost.communicator.repository.FoodRepository;

import java.util.List;

@Service
public class FoodService {

    private FoodRepository foodRepository;
    private BoardRepository boardRepository;

    @Autowired
    public void setFoodRepository(FoodRepository repository) {
        this.foodRepository = repository;
    }

    @Autowired
    public void setBoardRepository(BoardRepository repository) {
        this.boardRepository = repository;
    }

    public void incrementQuantity(long id) {
        incrementQuantity(this.foodRepository.getOne(id));
    }

    public void decrementQuantity(long id) {
        decrementQuantity(this.foodRepository.getOne(id));
    }

    public void setQuantity(long id, int quantity) {
        setQuantity(this.foodRepository.getOne(id), quantity);
    }

    public void incrementQuantity(Food food) {
        food.setQuantity(food.getQuantity()+1);
        this.foodRepository.save(food);
    }

    public void decrementQuantity(Food food) {
        food.setQuantity(food.getQuantity()-1);
        this.foodRepository.save(food);
    }

    public void setQuantity(Food food, int quantity) {
        food.setQuantity(quantity);
        this.foodRepository.save(food);
    }

    public Food getFoodById(long id) {
        return this.foodRepository.getOne(id);
    }

    public Food getFoodByBoardAndName(String boardId, String foodName) {
        List<Food> foods = this.boardRepository.getOne(boardId).getFoodList();
        for (Food food : foods) {
            if (food.getName().equals(foodName))
                return food;
        }
        return null;
    }

}
