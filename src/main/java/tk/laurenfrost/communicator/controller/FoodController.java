package tk.laurenfrost.communicator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.communicator.entity.Food;
import tk.laurenfrost.communicator.service.BoardService;
import tk.laurenfrost.communicator.service.FoodService;

import java.util.UUID;

// Proposal
// POST /api/v1/board/{boardId}/food|item
// POST /api/v1/food
// DELETE /api/v1/food/{foodId}
// PATCH /api/v1/food/{foodId}

@RestController
@RequestMapping(path = "api/v1/item")
public class FoodController {

    private BoardService boardService;
    private FoodService foodService;

    @Autowired
    public void setBoardService(BoardService service) {
        this.boardService = service;
    }

    @Autowired
    public void setFoodService(FoodService service) {
        this.foodService = service;
    }

    @PostMapping(value = "/{board}")
    public UUID addFoodToBoard(@PathVariable String board, @RequestBody String foodName) {
        return this.boardService.addFood(UUID.fromString(board), foodName);
    }

    @DeleteMapping(value = "/{food}")
    public String deleteFood(@PathVariable String food) {
        this.foodService.deleteFood(UUID.fromString(food));
        return "Food is deleted";
    }

    @PatchMapping(value = "/{foodId}")
    public String patchFood(@PathVariable String foodId, @RequestBody Food food) {
        this.foodService.incrementQuantity(UUID.fromString(foodId));
        return "Food quantity is incremented";
    }

    @PatchMapping(value = "/{food}/inc")
    public String increaseFood(@PathVariable String food) {
        this.foodService.incrementQuantity(UUID.fromString(food));
        return "Food quantity is incremented";
    }

    @PatchMapping(value = "/{food}/dec")
    public String decreaseFood(@PathVariable String food) {
        this.foodService.decrementQuantity(UUID.fromString(food));
        return "Food quantity is decremented";
    }

    @PatchMapping(value = "/{food}/set")
    public String setFoodQuantity(@PathVariable String food, @RequestParam Integer num) {
        if (num < 0)
            throw new IllegalArgumentException("Number of food cannot be negative!");
        this.foodService.setQuantity(UUID.fromString(food), num);
        return "Food quantity is set";
    }

}
