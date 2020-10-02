package tk.laurenfrost.communicator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.communicator.entity.Food;
import tk.laurenfrost.communicator.service.BoardService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1")
public class BoardController {

    private BoardService boardService;

    @Autowired
    public void setBoardService(BoardService service) {
        this.boardService = service;
    }

    @PostMapping(value = "/board/{boardName}")
    public UUID registerBoard(@PathVariable String boardName) {
        return this.boardService.createBoard(boardName);
    }

    @DeleteMapping(value = "/board/{board}")
    public String deleteBoard(@PathVariable String board) {
        this.boardService.deleteBoard(UUID.fromString(board));
        return "Board is deleted";
    }

    @DeleteMapping(value = "/list/{board}")
    public String clearBoardList(@PathVariable String board) {
        this.boardService.clearBoardFoodList(UUID.fromString(board));
        return "Board food list is cleared";
    }

    @GetMapping(value = "/list/{board}")
    public List<Food> getBoardList(@PathVariable String board) {
        return this.boardService.getBoardById(UUID.fromString(board)).getFoodList();
    }


}
