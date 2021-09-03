package com.example.hellomybatis.controller;

import com.example.hellomybatis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BoardController {
    @Autowired private BoardService boardService;

    @GetMapping("/v1/board")
    public List<Map<String, Object>> getBoards() {
        return boardService.getBoards();
    }
}
