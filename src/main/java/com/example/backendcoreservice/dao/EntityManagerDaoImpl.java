package com.example.backendcoreservice.dao;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@Component
public class EntityManagerDaoImpl implements EntityManagerDao {
    private final EntityManager entityManager;


    @Override
    public void updateQuery(String tableName, Long id, String columnName, Object value, String modifiedBy, LocalDateTime currentDate) {
        String query = "UPDATE " + tableName + " SET " + columnName + " = :value, modifiedBy = :modifiedBy, modifiedDate = :currentDate WHERE id = :id";
        entityManager.createQuery(query)
                .setParameter("value", value)
                .setParameter("modifiedBy", modifiedBy)
                .setParameter("currentDate", currentDate)
                .setParameter("id", id)
                .executeUpdate();

    }
}
