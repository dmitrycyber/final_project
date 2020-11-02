package by.epamtc.utilities.entity;

import java.io.Serializable;
import java.util.Objects;

public class WorkType implements Serializable {
    private int id;
    private String workType;

    public static class Builder{
        private int id;
        private String workType;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder workType(String workType){
            this.workType = workType;
            return this;
        }

        public WorkType build(){
            return new WorkType(this);
        }
    }

    private WorkType(Builder builder){
        id = builder.id;
        workType = builder.workType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkType workType1 = (WorkType) o;
        return id == workType1.id &&
                Objects.equals(workType, workType1.workType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workType);
    }

    @Override
    public String toString() {
        return "WorkType{" +
                "id=" + id +
                ", workType='" + workType + '\'' +
                '}';
    }
}
