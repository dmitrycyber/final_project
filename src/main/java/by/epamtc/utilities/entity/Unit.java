package by.epamtc.utilities.entity;

import java.io.Serializable;
import java.util.Objects;

public class Unit implements Serializable {
    private int id;
    private String title;

    public static class Builder{
        private int id;
        private String title;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Unit build(){
            return new Unit(this);
        }
    }

    private Unit(Builder builder){
        id = builder.id;
        title = builder.title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return id == unit.id &&
                Objects.equals(title, unit.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
