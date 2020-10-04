package by.epamtc.utilities.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import by.epamtc.utilities.util.Status;
import by.epamtc.utilities.util.Wrapper;
import by.epamtc.utilities.util.Wrapper.Builder;

public class RegData implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String surname;
	private String login;
	private String password;
	private String phoneNumber;
	private String street;
	private int house;
	private int flat;
	private String building;
	
	public static class Builder{
		private String name;
		private String surname;
		private String login;
		private String password;
		private String phoneNumber;
		private String street;
		private int house;
		private int flat;
		private String building;
		
		public Builder name(String name) {
            this.name = name;
            return this;
        }
		
		public Builder surname(String surname) {
            this.name = name;
            return this;
        }
		
		public Builder login(String login) {
            this.login = login;
            return this;
        }
		
		public Builder password(String password) {
            this.password = password;
            return this;
        }
		
		public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
		
		public Builder street(String street) {
            this.street = street;
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
		
		public Builder building(String building) {
            this.building = building;
            return this;
        }
		
		public RegData build() {
            return new RegData(this);
        }
	}
	
	private RegData(Builder builder) {
        name = builder.name;
        surname = builder.surname;
        login = builder.login;
        password = builder.password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegData other = (RegData) obj;
		if (building == null) {
			if (other.building != null)
				return false;
		} else if (!building.equals(other.building))
			return false;
		if (flat != other.flat)
			return false;
		if (house != other.house)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegData [name=" + name + ", surname=" + surname + ", login=" + login + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", street=" + street + ", house=" + house + ", flat=" + flat
				+ ", building=" + building + "]";
	}
	
	
	
	
	
	
	
}
