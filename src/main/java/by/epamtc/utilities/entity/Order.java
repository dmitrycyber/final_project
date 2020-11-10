package by.epamtc.utilities.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Order implements Serializable {
    private long id;
    private long workTypeId;
    private long userId;
    private long scaleUnitsId;
    private String userName;
    private String userSurname;
    private String workType;
    private String scaleUnit;

    private double scaleValue;
    private Timestamp startDate;
    private Timestamp endDate;
    private boolean isSeveral;
    private String description;

    private String status;

    private String address;
    private String street;
    private int house;
    private String building;
    private int flat;
    private String dispatcherComment;


    public static class Builder{
        private long id;
        private long workTypeId;
        private long userId;
        private long scaleUnitsId;

        private String userName;
        private String userSurname;
        private String workType;
        private String scaleUnit;

        private double scaleValue;
        private Timestamp startDate;
        private Timestamp endDate;
        private boolean isSeveral;
        private String description;

        private String status;

        private String address;
        private String street;
        private int house;
        private String building;
        private int flat;
        private String dispatcherComment;

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder building(String building) {
            this.building = building;
            return this;
        }

        public Builder house(int house) {
            this.house = house;
            return this;
        }

        public Builder flat(int flat) {
            this.flat = flat;
            return this;
        }


        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder userSurname(String userSurname) {
            this.userSurname = userSurname;
            return this;
        }

        public Builder workType(String workType) {
            this.workType = workType;
            return this;
        }

        public Builder scaleUnit(String scaleUnit) {
            this.scaleUnit = scaleUnit;
            return this;
        }



        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder workTypeId(int workTypeId) {
            this.workTypeId = workTypeId;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder scaleUnitsId(int scaleUnitsId) {
            this.scaleUnitsId = scaleUnitsId;
            return this;
        }

        public Builder scaleValue(double scaleValue) {
            this.scaleValue = scaleValue;
            return this;
        }

        public Builder startDate(Timestamp startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Timestamp endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder isSeveral(boolean isSeveral) {
            this.isSeveral = isSeveral;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder dispatcherComment(String dispatcherComment){
            this.dispatcherComment = dispatcherComment;
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }

    private Order(Builder builder){
        id = builder.id;
        workTypeId = builder.workTypeId;
        userId = builder.userId;
        scaleUnitsId = builder.scaleUnitsId;
        scaleValue = builder.scaleValue;
        startDate = builder.startDate;
        endDate = builder.endDate;
        isSeveral = builder.isSeveral;
        description = builder.description;

        userName = builder.userName;
        userSurname = builder.userSurname;
        workType = builder.workType;
        scaleUnit = builder.scaleUnit;
        status = builder.status;
        address = builder.address;
        street = builder.street;
        building = builder.building;
        house = builder.house;
        flat = builder.flat;
        dispatcherComment = builder.dispatcherComment;
    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(long workTypeId) {
        this.workTypeId = workTypeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getScaleUnitsId() {
        return scaleUnitsId;
    }

    public void setScaleUnitsId(long scaleUnitsId) {
        this.scaleUnitsId = scaleUnitsId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getScaleUnit() {
        return scaleUnit;
    }

    public void setScaleUnit(String scaleUnit) {
        this.scaleUnit = scaleUnit;
    }

    public double getScaleValue() {
        return scaleValue;
    }

    public void setScaleValue(double scaleValue) {
        this.scaleValue = scaleValue;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public boolean isSeveral() {
        return isSeveral;
    }

    public void setSeveral(boolean several) {
        isSeveral = several;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDispatcherComment() {
        return dispatcherComment;
    }

    public void setDispatcherComment(String dispatcherComment) {
        this.dispatcherComment = dispatcherComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                workTypeId == order.workTypeId &&
                userId == order.userId &&
                scaleUnitsId == order.scaleUnitsId &&
                Double.compare(order.scaleValue, scaleValue) == 0 &&
                isSeveral == order.isSeveral &&
                house == order.house &&
                flat == order.flat &&
                Objects.equals(userName, order.userName) &&
                Objects.equals(userSurname, order.userSurname) &&
                Objects.equals(workType, order.workType) &&
                Objects.equals(scaleUnit, order.scaleUnit) &&
                Objects.equals(startDate, order.startDate) &&
                Objects.equals(endDate, order.endDate) &&
                Objects.equals(description, order.description) &&
                Objects.equals(status, order.status) &&
                Objects.equals(address, order.address) &&
                Objects.equals(street, order.street) &&
                Objects.equals(building, order.building) &&
                Objects.equals(dispatcherComment, order.dispatcherComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workTypeId, userId, scaleUnitsId, userName, userSurname, workType, scaleUnit, scaleValue, startDate, endDate, isSeveral, description, status, address, street, house, building, flat, dispatcherComment);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", workTypeId=" + workTypeId +
                ", userId=" + userId +
                ", scaleUnitsId=" + scaleUnitsId +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", workType='" + workType + '\'' +
                ", scaleUnit='" + scaleUnit + '\'' +
                ", scaleValue=" + scaleValue +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isSeveral=" + isSeveral +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", building='" + building + '\'' +
                ", flat=" + flat +
                ", dispatcherComment='" + dispatcherComment + '\'' +
                '}';
    }
}
