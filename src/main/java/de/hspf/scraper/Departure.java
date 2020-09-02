package de.hspf.scraper;

public class Departure {
    private String time;
    private String actualTime;
    private String transportationType;
    private String transportationNumber;
    private String destination;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }

    public String getTransportationNumber() {
        return transportationNumber;
    }

    public void setTransportationNumber(String transportationNumber) {
        this.transportationNumber = transportationNumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }
}
