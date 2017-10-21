package com.kfc.kfconeone.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "update_info", schema = "badminton", catalog = "")
public class UpdateInfo {
    private Timestamp courtFlag;

    @Id
    @Column(name = "court_flag")
    public Timestamp getCourtFlag() {
        return courtFlag;
    }

    public void setCourtFlag(Timestamp courtFlag) {
        this.courtFlag = courtFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateInfo that = (UpdateInfo) o;

        if (courtFlag != null ? !courtFlag.equals(that.courtFlag) : that.courtFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return courtFlag != null ? courtFlag.hashCode() : 0;
    }
}
