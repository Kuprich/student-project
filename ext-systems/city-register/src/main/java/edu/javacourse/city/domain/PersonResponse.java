package edu.javacourse.city.domain;

public class PersonResponse {
    private boolean registered;
    private boolean temporal;

    public PersonResponse(boolean registered, boolean temporal) {
        this.registered = registered;
        this.temporal = temporal;
    }

    public PersonResponse() {
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public boolean isTemporal() {
        return temporal;
    }

    public void setTemporal(boolean temporal) {
        this.temporal = temporal;
    }
}
