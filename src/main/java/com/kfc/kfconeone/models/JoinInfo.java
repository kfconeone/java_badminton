package com.kfc.kfconeone.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "join_info", schema = "badminton", catalog = "")
public class JoinInfo {
    private int id;
    private String gatherId;
    private String accountId;
    private String information;
    private Timestamp playDate;
    private String name;
    private int confirm;
    private boolean isCancel;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "GatherId")
    public String getGatherId() {
        return gatherId;
    }

    public void setGatherId(String gatherId) {
        this.gatherId = gatherId;
    }

    @Basic
    @Column(name = "AccountId")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "Information")
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JoinInfo joinInfo = (JoinInfo) o;

        if (id != joinInfo.id) return false;
        if (gatherId != null ? !gatherId.equals(joinInfo.gatherId) : joinInfo.gatherId != null) return false;
        if (accountId != null ? !accountId.equals(joinInfo.accountId) : joinInfo.accountId != null) return false;
        if (information != null ? !information.equals(joinInfo.information) : joinInfo.information != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (gatherId != null ? gatherId.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (information != null ? information.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "PlayDate")
    public Timestamp getPlayDate() {
        return playDate;
    }

    public void setPlayDate(Timestamp playDate) {
        this.playDate = playDate;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Confirm")
    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    @Basic
    @Column(name = "IsCancel")
    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }
}
