package com.kfc.kfconeone.models;

import javax.persistence.*;

@Entity
@Table(name = "court_info", schema = "badminton", catalog = "")
public class CourtInfo {
    private String courtName;
    private String city;
    private String region;
    private String address;

    @Id
    @Column(name = "CourtName")
    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
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
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourtInfo courtInfo = (CourtInfo) o;

        if (courtName != null ? !courtName.equals(courtInfo.courtName) : courtInfo.courtName != null) return false;
        if (city != null ? !city.equals(courtInfo.city) : courtInfo.city != null) return false;
        if (region != null ? !region.equals(courtInfo.region) : courtInfo.region != null) return false;
        if (address != null ? !address.equals(courtInfo.address) : courtInfo.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = courtName != null ? courtName.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
