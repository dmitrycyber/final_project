package by.epamtc.utilities.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String login;
	private String role;
	private String name;

	public static class Builder{
		private long id;
		private String login;
		private String role;
		private String name;

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder login(String login) {
			this.login = login;
			return this;
		}

		public Builder role(String role) {
			this.role = role;
			return this;
		}

		public Builder name(String name){
			this.name = name;
			return this;
		}

		public User build(){
			return new User(this);
		}
	}

	public User() {
	}

	private User(Builder builder){
		id = builder.id;
		login = builder.login;
		role = builder.role;
		name = builder.name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
		User user = (User) o;
		return id == user.id &&
				Objects.equals(login, user.login) &&
				Objects.equals(role, user.role) &&
				Objects.equals(name, user.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, role, name);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", role='" + role + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
