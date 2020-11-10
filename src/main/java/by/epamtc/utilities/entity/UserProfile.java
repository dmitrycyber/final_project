package by.epamtc.utilities.entity;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private long userId;
    private String name;
    private String surname;
    private String login;
    private String oldLogin;
    private String phoneNumber;
    private String street;
    private int house;
    private int flat;
    private String building;
    private String position;
    private Timestamp hiringDate;

    public static class Builder {
        private long userId;
        private String name;
        private String surname;
        private String login;
        private String oldLogin;
        private String phoneNumber;
        private String street;
        private int house;
        private int flat;
        private String building;
        private String position;
        private Timestamp hiringDate;

        public UserProfile.Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public UserProfile.Builder name(String name) {
            this.name = name;
            return this;
        }

        public UserProfile.Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserProfile.Builder login(String login) {
            this.login = login;
            return this;
        }

        public UserProfile.Builder oldLogin(String oldLogin) {
            this.oldLogin = oldLogin;
            return this;
        }

        public UserProfile.Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserProfile.Builder street(String street) {
            this.street = street;
            return this;
        }

        public UserProfile.Builder house(int house) {
            this.house = house;
            return this;
        }

        public UserProfile.Builder flat(int flat) {
            this.flat = flat;
            return this;
        }

        public UserProfile.Builder building(String building) {
            this.building = building;
            return this;
        }

        public UserProfile.Builder position(String position) {
            this.position = position;
            return this;
        }

        public Builder hiringDate(Timestamp hiringDate){
            this.hiringDate = hiringDate;
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }

    private UserProfile(Builder builder) {
        userId = builder.userId;
        name = builder.name;
        surname = builder.surname;
        login = builder.login;
        oldLogin = builder.oldLogin;
        phoneNumber = builder.phoneNumber;
        street = builder.street;
        house = builder.house;
        flat = builder.flat;
        building = builder.building;
        position = builder.position;
        hiringDate = builder.hiringDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getOldLogin() {
        return oldLogin;
    }

    public void setOldLogin(String oldLogin) {
        this.oldLogin = oldLogin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Timestamp getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Timestamp hiringDate) {
        this.hiringDate = hiringDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return userId == that.userId &&
                house == that.house &&
                flat == that.flat &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(login, that.login) &&
                Objects.equals(oldLogin, that.oldLogin) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(street, that.street) &&
                Objects.equals(building, that.building) &&
                Objects.equals(position, that.position) &&
                Objects.equals(hiringDate, that.hiringDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, surname, login, oldLogin, phoneNumber, street, house, flat, building, position, hiringDate);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", oldLogin='" + oldLogin + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", flat=" + flat +
                ", building='" + building + '\'' +
                ", position='" + position + '\'' +
                ", hiringDate=" + hiringDate +
                '}';
    }
}
