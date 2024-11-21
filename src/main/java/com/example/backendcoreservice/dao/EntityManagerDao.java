package com.example.backendcoreservice.dao;

import java.time.LocalDateTime;

public interface EntityManagerDao {

    void updateQuery(String tableName, Long id, String columnName, Object value, String modifiedBy, LocalDateTime currentDate);
}
