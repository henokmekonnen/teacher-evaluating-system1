package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @author GHabtamu
 */
@Entity
@Table(name = "department")
public class Department extends BaseModel {
    private Integer departmentId;
    private String departmentName;
    private String description;
    private Integer numberOfStaff;

    @Id
    @Column(name = "DepartmentId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "DepartmentName", nullable = false, length = 100)
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    @Transient
    public Integer getId() {
        return getDepartmentId();
    }

    @Override
    public void setId(Integer id) {
        setDepartmentId(id);
    }


    @Basic
    @Column(name = "Description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "NumberOfStaff", nullable = false)
    public Integer getNumberOfStaff() {
        return numberOfStaff;
    }

    public void setNumberOfStaff(Integer numberOfStaff) {
        this.numberOfStaff = numberOfStaff;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentId, that.departmentId) &&
                Objects.equals(departmentName, that.departmentName) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(description, that.description) &&
                Objects.equals(numberOfStaff, that.numberOfStaff) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, departmentName, createdDate, createdBy, description, numberOfStaff);
    }
}
