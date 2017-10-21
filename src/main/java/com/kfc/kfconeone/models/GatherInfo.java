package com.kfc.kfconeone.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "gather_info", schema = "badminton", catalog = "")
public class GatherInfo {
    private String gatherId;
    private String accountId;
    private String teamName;
    private String teamLeader;
    private String city;
    private String region;
    private String court;
    private int price;
    private String grade;
    private int playersCount;
    private Timestamp playStartDateTime;
    private Timestamp playEndDateTime;
    private Timestamp submitDateTime;
    private String lineId;
    private String phone;
    private String facebookUrl;
    private String information;

    @Id
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
    @Column(name = "TeamName")
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "TeamLeader")
    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
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
    @Column(name = "Region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "Court")
    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    @Basic
    @Column(name = "Price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "PlayersCount")
    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    @Basic
    @Column(name = "PlayStartDateTime")
    public Timestamp getPlayStartDateTime() {
        return playStartDateTime;
    }

    public void setPlayStartDateTime(Timestamp playStartDateTime) {
        this.playStartDateTime = playStartDateTime;
    }

    @Basic
    @Column(name = "PlayEndDateTime")
    public Timestamp getPlayEndDateTime() {
        return playEndDateTime;
    }

    public void setPlayEndDateTime(Timestamp playEndDateTime) {
        this.playEndDateTime = playEndDateTime;
    }

    @Basic
    @Column(name = "SubmitDateTime")
    public Timestamp getSubmitDateTime() {
        return submitDateTime;
    }

    public void setSubmitDateTime(Timestamp submitDateTime) {
        this.submitDateTime = submitDateTime;
    }

    @Basic
    @Column(name = "LineId")
    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
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
    @Column(name = "FacebookUrl")
    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
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

        GatherInfo that = (GatherInfo) o;

        if (price != that.price) return false;
        if (playersCount != that.playersCount) return false;
        if (gatherId != null ? !gatherId.equals(that.gatherId) : that.gatherId != null) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (teamName != null ? !teamName.equals(that.teamName) : that.teamName != null) return false;
        if (teamLeader != null ? !teamLeader.equals(that.teamLeader) : that.teamLeader != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (court != null ? !court.equals(that.court) : that.court != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (playStartDateTime != null ? !playStartDateTime.equals(that.playStartDateTime) : that.playStartDateTime != null)
            return false;
        if (playEndDateTime != null ? !playEndDateTime.equals(that.playEndDateTime) : that.playEndDateTime != null)
            return false;
        if (submitDateTime != null ? !submitDateTime.equals(that.submitDateTime) : that.submitDateTime != null)
            return false;
        if (lineId != null ? !lineId.equals(that.lineId) : that.lineId != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (facebookUrl != null ? !facebookUrl.equals(that.facebookUrl) : that.facebookUrl != null) return false;
        if (information != null ? !information.equals(that.information) : that.information != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gatherId != null ? gatherId.hashCode() : 0;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        result = 31 * result + (teamLeader != null ? teamLeader.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (court != null ? court.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + playersCount;
        result = 31 * result + (playStartDateTime != null ? playStartDateTime.hashCode() : 0);
        result = 31 * result + (playEndDateTime != null ? playEndDateTime.hashCode() : 0);
        result = 31 * result + (submitDateTime != null ? submitDateTime.hashCode() : 0);
        result = 31 * result + (lineId != null ? lineId.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (facebookUrl != null ? facebookUrl.hashCode() : 0);
        result = 31 * result + (information != null ? information.hashCode() : 0);
        return result;
    }
}
