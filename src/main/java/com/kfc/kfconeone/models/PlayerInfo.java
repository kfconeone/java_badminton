package com.kfc.kfconeone.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "player_info", schema = "badminton", catalog = "")
public class PlayerInfo {
    private String accountId;
    private String uId;
    private String name;
    private String eMail;
    private String phone;
    private String fbId;
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
    @Column(name = "UId")
    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "EMail")
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "Phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "FBId")
    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
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
        if (uId != null ? !uId.equals(that.uId) : that.uId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (eMail != null ? !eMail.equals(that.eMail) : that.eMail != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (fbId != null ? !fbId.equals(that.fbId) : that.fbId != null) return false;
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
        result = 31 * result + (uId != null ? uId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (fbId != null ? fbId.hashCode() : 0);
        result = 31 * result + (teamTemplate != null ? teamTemplate.hashCode() : 0);
        result = 31 * result + (teamTemplateHistory != null ? teamTemplateHistory.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        return result;
    }
}
