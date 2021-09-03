package com.example.hellomybatis.repository;

import java.util.List;
import java.util.Map;

public interface BoardRepository {
    List<Map<String,Object>> getBoards();
}
