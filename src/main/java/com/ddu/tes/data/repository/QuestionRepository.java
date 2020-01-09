package com.ddu.tes.data.repository;

import com.ddu.tes.data.modle.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
