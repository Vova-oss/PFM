package com.example.demo.Repositories;

import com.example.demo.Entities.TransactionData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDataRepository extends JpaRepository<TransactionData, Long> {
}
