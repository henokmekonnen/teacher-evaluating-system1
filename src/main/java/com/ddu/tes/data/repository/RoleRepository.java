package com.ddu.tes.data.repository;

import com.ddu.tes.data.modle.Role;
import com.ddu.tes.data.modle.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author GHabtamu
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
