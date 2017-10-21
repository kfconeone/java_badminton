package com.kfc.kfconeone.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "error_info", schema = "badminton", catalog = "")
public class ErrorInfo {
    private int id;
    private Timestamp occurTime;
    private String message;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "OccurTime")
    public Timestamp getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(Timestamp occurTime) {
        this.occurTime = occurTime;
    }

    @Basic
    @Column(name = "Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorInfo errorInfo = (ErrorInfo) o;

        if (id != errorInfo.id) return false;
        if (occurTime != null ? !occurTime.equals(errorInfo.occurTime) : errorInfo.occurTime != null) return false;
        if (message != null ? !message.equals(errorInfo.message) : errorInfo.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (occurTime != null ? occurTime.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
