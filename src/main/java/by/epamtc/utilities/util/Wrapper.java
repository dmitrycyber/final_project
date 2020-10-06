package by.epamtc.utilities.util;

import java.io.Serializable;


public class Wrapper<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Status status;
	private T message;
	
	public static class Builder<T> {
		private Status status;
		private T message;

        public Builder<T> status(Status status) {
            this.status = status;
            return this;
        }

        public Builder<T> message(Object message) {
            this.message = (T) message;
            return this;
        }
        
        public Wrapper<T> build() {
            return new Wrapper<T>(this);
        }

    }
	
	private Wrapper(Builder<?> builder) {
        status = builder.status;
        message = (T) builder.message;
    }

	
	public Wrapper() {
		super();
	}
	
	public Wrapper(Status status, T message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public T getMessage() {
		return message;
	}
	public void setMessage(T message) {
		this.message = message;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Wrapper<?> other = (Wrapper<?>) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Wrapper [status=" + status + ", message=" + message + "]";
	}
	
	
	

}
