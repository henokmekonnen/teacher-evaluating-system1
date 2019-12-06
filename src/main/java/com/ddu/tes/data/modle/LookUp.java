package com.ddu.tes.data.modle;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author GHabtamu
 */
@Entity
@Table(name = "look_up")
public class LookUp {
    private String lookUpId;
    private String description;

    @Id
    @Column(name = "LookUpId", nullable = false, length = 10)
    public String getLookUpId() {
        return lookUpId;
    }

    public void setLookUpId(String lookUpId) {
        this.lookUpId = lookUpId;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LookUp lookUp = (LookUp) o;
        return Objects.equals(lookUpId, lookUp.lookUpId) &&
                Objects.equals(description, lookUp.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lookUpId, description);
    }
}
