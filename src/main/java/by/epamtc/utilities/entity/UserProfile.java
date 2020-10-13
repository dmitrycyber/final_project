package by.epamtc.utilities.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private String login;
    private String phoneNumber;
    private String street;
    private int house;
    private int flat;
    private String building;

    public static class Builder{
        private String name;
        private String surname;
        private String login;
        private String phoneNumber;
        private String street;
        private int house;
        private int flat;
        private String building;

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

        public UserProfile build() {
            return new UserProfile(this);
        }
    }

    private UserProfile(Builder builder) {
        name = builder.name;
        surname = builder.surname;
        login = builder.login;
        phoneNumber = builder.phoneNumber;
        street = builder.street;
        house = builder.house;
        flat = builder.flat;
        building = builder.building;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((building == null) ? 0 : building.hashCode());
        result = prime * result + flat;
        result = prime * result + house;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return house == that.house &&
                flat == that.flat &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(login, that.login) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(street, that.street) &&
                Objects.equals(building, that.building);
    }

    @Override
    public String toString() {
        return "RegData [name=" + name + ", surname=" + surname + ", login=" + login
                + ", phoneNumber=" + phoneNumber + ", street=" + street + ", house=" + house + ", flat=" + flat
                + ", building=" + building + "]";
    }







}
