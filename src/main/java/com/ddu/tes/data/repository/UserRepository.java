package com.ddu.tes.data.repository;

import com.ddu.tes.data.modle.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
