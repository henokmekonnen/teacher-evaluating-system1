package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "lu_process_channel")
public class ProcessChannel {
    private String processChannelId;
    private Integer createdByUserId;
    private Timestamp createdDate;
    private String description;
    private Timestamp lastUpdatedDate;

    @Id
    @Column(name = "ProcessChannelId", nullable = false, length = 100)
    public String getProcessChannelId() {
        return processChannelId;
    }

    public void setProcessChannelId(String processChannelId) {
        this.processChannelId = processChannelId;
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
    @Column(name = "CreatedDate", nullable = false)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "LastUpdatedDate", nullable = false)
    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessChannel that = (ProcessChannel) o;
        return Objects.equals(processChannelId, that.processChannelId) &&
                Objects.equals(createdByUserId, that.createdByUserId) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(description, that.description) &&
                Objects.equals(lastUpdatedDate, that.lastUpdatedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(processChannelId, createdByUserId, createdDate, description, lastUpdatedDate);
    }
}
