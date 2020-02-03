package com.ddu.tes.data.modle;

import com.ddu.tes.data.enums.ProcessRelationTypeEnum;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "process_relation")
public class ProcessRelation extends BaseModel {
    private Integer processRelationId;
    private Integer createdByUserId;
    private Integer primaryProcessId;
    private Integer secondaryProcessId;
    private ProcessRelationTypeEnum processRelationTypeId;

    @Id
    @Column(name = "ProcessRelationId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getProcessRelationId() {
        return processRelationId;
    }

    public void setProcessRelationId(Integer processRelationId) {
        this.processRelationId = processRelationId;
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
    @Column(name = "PrimaryProcessId", nullable = false)
    public Integer getPrimaryProcessId() {
        return primaryProcessId;
    }

    public void setPrimaryProcessId(Integer primaryProcessId) {
        this.primaryProcessId = primaryProcessId;
    }

    @Basic
    @Column(name = "SecondaryProcessId", nullable = false)
    public Integer getSecondaryProcessId() {
        return secondaryProcessId;
    }

    public void setSecondaryProcessId(Integer secondaryProcessId) {
        this.secondaryProcessId = secondaryProcessId;
    }

    @Basic
    @Enumerated(value = EnumType.STRING)
    @Column(name = "ProcessRelationTypeId", nullable = false)
    public ProcessRelationTypeEnum getProcessRelationTypeId() {
        return processRelationTypeId;
    }

    public void setProcessRelationTypeId(ProcessRelationTypeEnum processRelationTypeId) {
        this.processRelationTypeId = processRelationTypeId;
    }

    @Transient
    @Override
    public Integer getId() {
        return getProcessRelationId();
    }

    @Override
    public void setId(Integer id) {
        setProcessRelationId(id);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessRelation that = (ProcessRelation) o;
        return Objects.equals(processRelationId, that.processRelationId) &&
                Objects.equals(createdByUserId, that.createdByUserId) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(primaryProcessId, that.primaryProcessId) &&
                Objects.equals(secondaryProcessId, that.secondaryProcessId) &&
                Objects.equals(processRelationTypeId, that.processRelationTypeId) &&
                Objects.equals(lastUpdatedDate, that.lastUpdatedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(processRelationId, createdByUserId, createdDate, primaryProcessId, secondaryProcessId, processRelationTypeId, lastUpdatedDate);
    }
}
