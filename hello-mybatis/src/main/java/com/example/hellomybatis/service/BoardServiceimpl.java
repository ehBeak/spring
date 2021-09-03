package com.example.hellomybatis.service;

import com.example.hellomybatis.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class BoardServiceimpl implements BoardService{
    @Autowired private BoardRepository boardRepository;

    @Override
    public List<Map<String, Object>> getBoards() {
        return boardRepository.getBoards();
    }
}
