package com.ddu.tes.data.repository;

import com.ddu.tes.data.modle.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author GHabtamu
 */
public interface DepartmentRepository extends JpaRepository <Department,Long>{

    @Query(value = "select count(*) from department d",nativeQuery= true)
    int countDepartment();

    @Query(value = "select count(*) from department d where d.departmentName = :name",nativeQuery= true)
    int countDepartment(String name);
}
