package com.ddu.tes.data.modle;

import com.ddu.tes.data.enums.MessageTypeEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "process_message")
public class ProcessMessage extends BaseModel {
    private Integer processMessageId;
    private Integer createdByUserId;
    private Integer processId;
    private MessageTypeEnum messageTypeId;
    private Integer toUserId;
    private String message;
    private String recipentContact;
    private Date expirationTimestamp;
    private Boolean messageStatusId;
    private String statusMesage;

    @Transient
    @Override
    public Integer getId() {
        return getProcessMessageId();
    }

    @Override
    public void setId(Integer id) {
           setProcessMessageId(id);
    }

    @Id
    @Column(name = "ProcessMessageId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getProcessMessageId() {
        return processMessageId;
    }

    public void setProcessMessageId(Integer processMessageId) {
        this.processMessageId = processMessageId;
    }

    @Basic
    @Column(name = "CreatedByUserId", nullable = true)
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
    @Enumerated(EnumType.STRING)
    @Column(name = "MessageTypeId", nullable = false, length = 100)
    public MessageTypeEnum getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(MessageTypeEnum messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Basic
    @Column(name = "ToUserId", nullable = true)
    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    @Basic
    @Column(name = "Message", nullable = true, length = -1)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "RecipentContact", nullable = true, length = 100)
    public String getRecipentContact() {
        return recipentContact;
    }

    public void setRecipentContact(String recipentContact) {
        this.recipentContact = recipentContact;
    }

    @Basic
    @Column(name = "ExpirationTimestamp", nullable = true)
    public Date getExpirationTimestamp() {
        return expirationTimestamp;
    }

    public void setExpirationTimestamp(Date expirationTimestamp) {
        this.expirationTimestamp = expirationTimestamp;
    }

    @Basic
    @Column(name = "MessageStatusId", nullable = false)
    public Boolean getMessageStatusId() {
        return messageStatusId;
    }

    public void setMessageStatusId(Boolean messageStatusId) {
        this.messageStatusId = messageStatusId;
    }

    @Basic
    @Column(name = "StatusMesage", nullable = true, length = 1000)
    public String getStatusMesage() {
        return statusMesage;
    }

    public void setStatusMesage(String statusMesage) {
        this.statusMesage = statusMesage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessMessage that = (ProcessMessage) o;
        return processMessageId == that.processMessageId &&
                processId == that.processId &&
                messageStatusId == that.messageStatusId &&
                Objects.equals(createdByUserId, that.createdByUserId) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(messageTypeId, that.messageTypeId) &&
                Objects.equals(toUserId, that.toUserId) &&
                Objects.equals(message, that.message) &&
                Objects.equals(recipentContact, that.recipentContact) &&
                Objects.equals(expirationTimestamp, that.expirationTimestamp) &&
                Objects.equals(statusMesage, that.statusMesage) &&
                Objects.equals(lastUpdatedDate, that.lastUpdatedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(processMessageId, createdByUserId, createdDate, processId, messageTypeId, toUserId, message, recipentContact, expirationTimestamp, messageStatusId, statusMesage, lastUpdatedDate);
    }
}
