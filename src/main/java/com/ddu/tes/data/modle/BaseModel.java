package com.ddu.tes.data.modle;

import com.ddu.tes.data.convertor.DateTimeConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author abraham 12/02/2018
 */
@MappedSuperclass
public abstract class BaseModel implements Serializable {

    @Column
    protected String createdBy;

    @Column
    @CreationTimestamp
    @Convert(converter = DateTimeConverter.class)
    protected Date createdDate;

    @Column
    @UpdateTimestamp
    @Convert(converter = DateTimeConverter.class)
    protected  Date lastUpdatedDate;

    /**
     * implemented by every model entity to return its ID value
     *
     * @return
     */
    @Transient
    public abstract Integer getId();

    /**
     * @param id
     */
    @Transient
    public abstract void setId(final Integer id);

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
