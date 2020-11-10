package by.epamtc.utilities.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Note {
    private int id;
    private int workTypeId;
    private int orderId;
    private int brigadeId;
    private long dispatcherId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String comment;
    private String workType;
    private List<Long> employeeIds;
    private boolean isDecline;

    private String street;
    private int house;
    private String building;
    private int flat;
    private String description;
    private String phoneNumber;
    private List<String> employeeSurnames;
    private String name;

    public static class Builder{
        private int id;
        private int workTypeId;
        private int orderId;
        private int brigadeId;
        private long dispatcherId;
        private Timestamp startDate;
        private Timestamp endDate;
        private String comment;
        private String workType;
        private List<Long> employeeIds;
        private boolean isDecline;

        private String street;
        private int house;
        private String building;
        private int flat;
        private String description;
        private String phoneNumber;
        private List<String> employeeSurnames;
        private String name;

        public Builder street(String street){
            this.street = street;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder building(String building){
            this.building = building;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder house(int house){
            this.house = house;
            return this;
        }

        public Builder flat(int flat){
            this.flat = flat;
            return this;
        }

        public Builder employeeSurnames(List<String> employeeSurnames){
            this.employeeSurnames = employeeSurnames;
            return this;
        }

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder workTypeId(int workTypeId){
            this.workTypeId = workTypeId;
            return this;
        }

        public Builder orderId(int orderId){
            this.orderId = orderId;
            return this;
        }

        public Builder brigadeId(int brigadeId){
            this.brigadeId = brigadeId;
            return this;
        }

        public Builder dispatcherId(long dispatcherId){
            this.dispatcherId = dispatcherId;
            return this;
        }

        public Builder startDate(Timestamp startDate){
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Timestamp endDate){
            this.endDate = endDate;
            return this;
        }

        public Builder comment(String comment){
            this.comment = comment;
            return this;
        }

        public Builder workType(String workType){
            this.workType = workType;
            return this;
        }

        public Builder employeeIds(List<Long> employeeIds){
            this.employeeIds = employeeIds;
            return this;
        }

        public Builder isDecline(boolean isDecline){
            this.isDecline = isDecline;
            return this;
        }

        public Note build(){
            return new Note(this);
        }
    }

    private Note (Builder builder){
        id = builder.id;
        workTypeId = builder.workTypeId;
        orderId = builder.orderId;
        brigadeId = builder.brigadeId;
        dispatcherId = builder.dispatcherId;
        startDate = builder.startDate;
        endDate = builder.endDate;
        comment = builder.comment;
        workType = builder.workType;
        employeeIds = builder.employeeIds;
        isDecline = builder.isDecline;
        street = builder.street;
        house = builder.house;
        building = builder.building;
        flat = builder.flat;
        description = builder.description;
        phoneNumber = builder.phoneNumber;
        employeeSurnames = builder.employeeSurnames;
        name = builder.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(int workTypeId) {
        this.workTypeId = workTypeId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(int brigadeId) {
        this.brigadeId = brigadeId;
    }

    public long getDispatcherId() {
        return dispatcherId;
    }

    public void setDispatcherId(long dispatcherId) {
        this.dispatcherId = dispatcherId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public boolean isDecline() {
        return isDecline;
    }

    public void setDecline(boolean decline) {
        isDecline = decline;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getEmployeeSurnames() {
        return employeeSurnames;
    }

    public void setEmployeeSurnames(List<String> employeeSurnames) {
        this.employeeSurnames = employeeSurnames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id &&
                workTypeId == note.workTypeId &&
                orderId == note.orderId &&
                brigadeId == note.brigadeId &&
                dispatcherId == note.dispatcherId &&
                isDecline == note.isDecline &&
                house == note.house &&
                flat == note.flat &&
                Objects.equals(startDate, note.startDate) &&
                Objects.equals(endDate, note.endDate) &&
                Objects.equals(comment, note.comment) &&
                Objects.equals(workType, note.workType) &&
                Objects.equals(employeeIds, note.employeeIds) &&
                Objects.equals(street, note.street) &&
                Objects.equals(building, note.building) &&
                Objects.equals(description, note.description) &&
                Objects.equals(phoneNumber, note.phoneNumber) &&
                Objects.equals(employeeSurnames, note.employeeSurnames) &&
                Objects.equals(name, note.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workTypeId, orderId, brigadeId, dispatcherId, startDate, endDate, comment, workType, employeeIds, isDecline, street, house, building, flat, description, phoneNumber, employeeSurnames, name);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", workTypeId=" + workTypeId +
                ", orderId=" + orderId +
                ", brigadeId=" + brigadeId +
                ", dispatcherId=" + dispatcherId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", comment='" + comment + '\'' +
                ", workType='" + workType + '\'' +
                ", employeeIds=" + employeeIds +
                ", isDecline=" + isDecline +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", building='" + building + '\'' +
                ", flat=" + flat +
                ", description='" + description + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", employeeSurnames=" + employeeSurnames +
                ", dispatcherSurname='" + name + '\'' +
                '}';
    }
}
