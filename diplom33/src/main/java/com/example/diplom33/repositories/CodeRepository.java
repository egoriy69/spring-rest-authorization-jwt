package com.example.diplom33.repositories;


import com.example.diplom33.models.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Integer> {
   Optional<Code> findByCode(String code);
}
