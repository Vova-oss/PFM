package com.example.demo.Repository;

import com.example.demo.Entity.MCC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MCCRepository extends JpaRepository<MCC, Long> {
}
