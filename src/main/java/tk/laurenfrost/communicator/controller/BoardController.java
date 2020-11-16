package tk.laurenfrost.communicator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.communicator.entity.Board;
import tk.laurenfrost.communicator.entity.Food;
import tk.laurenfrost.communicator.mqtt.MqttService;
import tk.laurenfrost.communicator.service.BoardService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/board")
public class BoardController {

    private BoardService boardService;
    private MqttService mqttService;

    @Autowired
    public void setBoardService(BoardService service) {
        this.boardService = service;
    }

    @Autowired
    public void setMqttService(MqttService service) {
        this.mqttService = service;
    }

    @PostMapping
    public Board registerBoard(@RequestBody Board board) {
        this.mqttService.listenBoard(board.getMacAddress());
        return this.boardService.createBoard(board);
    }

    @DeleteMapping(value = "/{board}")
    public ResponseEntity<?> deleteBoard(@PathVariable String board) {
        this.mqttService.stopListeningBoard(this.boardService.getBoardById(UUID.fromString(board)).getMacAddress());
        this.boardService.deleteBoard(UUID.fromString(board));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
