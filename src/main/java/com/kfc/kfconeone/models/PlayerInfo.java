package com.kfc.kfconeone.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "player_info", schema = "badminton", catalog = "")
public class PlayerInfo {
    private String accountId;
    private String deviceId;
    private String teamTemplate;
    private String teamTemplateHistory;
    private String city;
    private Timestamp registerDate;

    @Id
    @Column(name = "AccountId")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "DeviceId")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "TeamTemplate")
    public String getTeamTemplate() {
        return teamTemplate;
    }

    public void setTeamTemplate(String teamTemplate) {
        this.teamTemplate = teamTemplate;
    }

    @Basic
    @Column(name = "TeamTemplateHistory")
    public String getTeamTemplateHistory() {
        return teamTemplateHistory;
    }

    public void setTeamTemplateHistory(String teamTemplateHistory) {
        this.teamTemplateHistory = teamTemplateHistory;
    }

    @Basic
    @Column(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "RegisterDate")
    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerInfo that = (PlayerInfo) o;

        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (deviceId != null ? !deviceId.equals(that.deviceId) : that.deviceId != null) return false;
        if (teamTemplate != null ? !teamTemplate.equals(that.teamTemplate) : that.teamTemplate != null) return false;
        if (teamTemplateHistory != null ? !teamTemplateHistory.equals(that.teamTemplateHistory) : that.teamTemplateHistory != null)
            return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (registerDate != null ? !registerDate.equals(that.registerDate) : that.registerDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        result = 31 * result + (teamTemplate != null ? teamTemplate.hashCode() : 0);
        result = 31 * result + (teamTemplateHistory != null ? teamTemplateHistory.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        return result;
    }
}
