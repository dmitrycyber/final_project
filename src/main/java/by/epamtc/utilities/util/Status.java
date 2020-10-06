package by.epamtc.utilities.util;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Status {
	NONE(0, ""),
    SUCCESSFULL(200, "successfull"),
    DECLINE(400, "decline"),
    LOGIN_OCCUPIED(400, "decline");


    private static final Map<Integer, Status> lookup = new HashMap<>();


    static {
        for (Status type : EnumSet.allOf(Status.class)) {
            lookup.put(type.getValue(), type);
        }
    }

    private int value;
    private String message;


    Status(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public static Status get(int value) {
        if (lookup.containsKey(value))
            return lookup.get(value);
        return Status.NONE;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

	
	
	
}
