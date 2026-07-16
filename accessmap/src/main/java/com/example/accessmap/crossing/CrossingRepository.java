package com.example.accessmap.crossing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrossingRepository extends JpaRepository<CrossingEntity, Long> {
}
