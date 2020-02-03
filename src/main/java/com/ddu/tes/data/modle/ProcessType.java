package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lu_process_type")
public class ProcessType extends BaseModel {
    private String processTypeId;
    private Integer createdByUserId;
    private String description;

    @Id
    @Column(name = "ProcessTypeId", nullable = false, length = 100)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getProcessTypeId() {
        return processTypeId;
    }

    public void setProcessTypeId(String processTypeId) {
        this.processTypeId = processTypeId;
    }

    @Basic
    @Column(name = "CreatedByUserId", nullable = false)
    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }


    @Basic
    @Column(name = "Description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessType that = (ProcessType) o;
        return Objects.equals(processTypeId, that.processTypeId) &&
                Objects.equals(createdByUserId, that.createdByUserId) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(lastUpdatedDate, that.lastUpdatedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(processTypeId, createdByUserId, createdDate, description, lastUpdatedDate);
    }
}
