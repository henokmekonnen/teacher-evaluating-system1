package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "process_data")
public class ProcessData extends BaseModel {
    private Integer processDataId;
    private Integer createdByUserId;
    private Integer processId;
    private String dataName;
    private String dataValue;

    @Id
    @Column(name = "ProcessDataId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getProcessDataId() {
        return processDataId;
    }

    public void setProcessDataId(Integer processDataId) {
        this.processDataId = processDataId;
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
    @Column(name = "ProcessId", nullable = false)
    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    @Basic
    @Column(name = "DataName", nullable = false, length = 100)
    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    @Basic
    @Column(name = "DataValue", nullable = true, length = -1)
    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }


    @Transient
    @Override
    public Integer getId() {
        return getProcessId();
    }

    @Override
    public void setId(Integer id) {
        setProcessId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessData that = (ProcessData) o;
        return Objects.equals(processDataId, that.processDataId) &&
                Objects.equals(createdByUserId, that.createdByUserId) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(processId, that.processId) &&
                Objects.equals(dataName, that.dataName) &&
                Objects.equals(dataValue, that.dataValue) &&
                Objects.equals(lastUpdatedDate, that.lastUpdatedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(processDataId, createdByUserId, createdDate, processId, dataName, dataValue, lastUpdatedDate);
    }
}
