package com.ddu.tes.data.modle;

import com.ddu.tes.data.enums.ChannelEnum;
import com.ddu.tes.data.enums.ProcessStatusEnum;
import com.ddu.tes.data.enums.ProcessTypeEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "process")
public class Process extends BaseModel {
    private Integer processId;
    private Integer createdByUserId;
    private Integer initiatingUserId;
    private Integer executingUserId;
    private Date startedTimestamp;
    private Date completedTimestamp;
    private ChannelEnum processChannelId;
    private ProcessStatusEnum processStatusId;
    private ProcessTypeEnum processTypeId;
    private String code;
    private String statusMessage;


    @Id
    @Column(name = "ProcessId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
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
    @Column(name = "InitiatingUserId", nullable = true)
    public Integer getInitiatingUserId() {
        return initiatingUserId;
    }

    public void setInitiatingUserId(Integer initiatingUserId) {
        this.initiatingUserId = initiatingUserId;
    }

    @Basic
    @Column(name = "ExecutingUserId", nullable = false)
    public Integer getExecutingUserId() {
        return executingUserId;
    }

    public void setExecutingUserId(Integer executingUserId) {
        this.executingUserId = executingUserId;
    }


    @Basic
    @Column(name = "StartedTimestamp", nullable = false)
    public Date getStartedTimestamp() {
        return startedTimestamp;
    }

    public void setStartedTimestamp(Date startedTimestamp) {
        this.startedTimestamp = startedTimestamp;
    }

    @Basic
    @Column(name = "CompletedTimestamp", nullable = true)
    public Date getCompletedTimestamp() {
        return completedTimestamp;
    }

    public void setCompletedTimestamp(Date completedTimestamp) {
        this.completedTimestamp = completedTimestamp;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "ProcessChannelId", nullable = false, length = 100)
    public ChannelEnum getProcessChannelId() {
        return processChannelId;
    }

    public void setProcessChannelId(ChannelEnum processChannelId) {
        this.processChannelId = processChannelId;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "ProcessStatusId", nullable = false, length = 100)
    public ProcessStatusEnum getProcessStatusId() {
        return processStatusId;
    }

    public void setProcessStatusId(ProcessStatusEnum processStatusId) {
        this.processStatusId = processStatusId;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "ProcessTypeId", nullable = false, length = 100)
    public ProcessTypeEnum getProcessTypeId() {
        return processTypeId;
    }

    public void setProcessTypeId(ProcessTypeEnum processTypeId) {
        this.processTypeId = processTypeId;
    }

    @Basic
    @Column(name = "Code", nullable = false, length = 30)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "StatusMessage", nullable = true, length = -1)
    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
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
        Process process = (Process) o;
        return Objects.equals(processId, process.processId) &&
                Objects.equals(createdByUserId, process.createdByUserId) &&
                Objects.equals(createdDate, process.createdDate) &&
                Objects.equals(initiatingUserId, process.initiatingUserId) &&
                Objects.equals(executingUserId, process.executingUserId) &&
                Objects.equals(startedTimestamp, process.startedTimestamp) &&
                Objects.equals(completedTimestamp, process.completedTimestamp) &&
                Objects.equals(processChannelId, process.processChannelId) &&
                Objects.equals(processStatusId, process.processStatusId) &&
                Objects.equals(processTypeId, process.processTypeId) &&
                Objects.equals(code, process.code) &&
                Objects.equals(statusMessage, process.statusMessage) &&
                Objects.equals(lastUpdatedDate, process.lastUpdatedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(processId, createdByUserId, createdDate, initiatingUserId, executingUserId, startedTimestamp, completedTimestamp, processChannelId, processStatusId, processTypeId, code, statusMessage, lastUpdatedDate);
    }
}
