package by.epamtc.utilities.dto;

import java.io.BufferedOutputStream;
import java.io.Serializable;
import java.util.Objects;

public class WorkDescriptionDto implements Serializable {
    private String name;
    private String surname;
    private String street;
    private int house;
    private String building;
    private int flat;
    private String description;

    public static class Builder{
        private String name;
        private String surname;
        private String street;
        private int house;
        private String building;
        private int flat;
        private String description;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder surname(String surname){
            this.surname = surname;
            return this;
        }

        public Builder street(String street){
            this.street = street;
            return this;
        }

        public Builder house(int house){
            this.house = house;
            return this;
        }

        public Builder building(String building){
            this.building = building;
            return this;
        }

        public Builder flat(int flat){
            this.flat = flat;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public WorkDescriptionDto build(){
            return new WorkDescriptionDto(this);
        }
    }

    private WorkDescriptionDto(Builder builder){
        name = builder.name;
        surname = builder.surname;
        street = builder.street;
        house = builder.house;
        building = builder.building;
        flat = builder.flat;
        description = builder.description;
    }

    public WorkDescriptionDto() {
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

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

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
        WorkDescriptionDto that = (WorkDescriptionDto) o;
        return house == that.house &&
                flat == that.flat &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(street, that.street) &&
                Objects.equals(building, that.building) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, street, house, building, flat, description);
    }

    @Override
    public String toString() {
        return "WorkDescriptionDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", building='" + building + '\'' +
                ", flat=" + flat +
                ", description='" + description + '\'' +
                '}';
    }
}
