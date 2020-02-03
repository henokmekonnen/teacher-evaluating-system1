package com.ddu.tes.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ddu.tes.data.modle.Process;



public interface ProcessRepository extends JpaRepository<Process, Long> {
}
