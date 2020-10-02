package tk.laurenfrost.communicator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.laurenfrost.communicator.entity.Food;
import tk.laurenfrost.communicator.repository.BoardRepository;
import tk.laurenfrost.communicator.repository.FoodRepository;

import java.util.UUID;

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

    public void incrementQuantity(UUID id) {
        Food food = this.foodRepository.getOne(id);
        food.setQuantity(food.getQuantity()+1);
        this.foodRepository.save(food);
    }

    public void decrementQuantity(UUID id) {
        Food food = this.foodRepository.getOne(id);
        if (food.getQuantity() == 1)
            this.foodRepository.delete(food);
        else {
            food.setQuantity(food.getQuantity() - 1);
            this.foodRepository.save(food);
        }
    }

    public void setQuantity(UUID id, int quantity) {
        Food food = this.foodRepository.getOne(id);
        if (quantity == 0)
            this.foodRepository.delete(food);
        else {
            food.setQuantity(quantity);
            this.foodRepository.save(food);
        }
    }

    public void deleteFood(UUID food) {
        this.foodRepository.deleteById(food);
    }

}
