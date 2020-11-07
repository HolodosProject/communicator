package tk.laurenfrost.communicator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.communicator.entity.Food;
import tk.laurenfrost.communicator.service.FoodService;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/item")
public class FoodController {

    private FoodService foodService;

    @Autowired
    public void setFoodService(FoodService service) {
        this.foodService = service;
    }


    @PostMapping
    public Food createFood(@RequestBody Food food) {
        return this.foodService.createFood(food);
    }

    @DeleteMapping(value = "/{foodId}")
    public ResponseEntity<?> deleteFood(@PathVariable String foodId) {
        this.foodService.deleteFood(UUID.fromString(foodId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping(value = "/{foodId}")
    public Food patchFood(@RequestBody Food food) {
        return this.foodService.saveFood(food);
    }

}
