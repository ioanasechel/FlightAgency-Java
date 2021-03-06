package Domain;

import java.util.List;

public class Ticket extends Entity<Integer>{

    private Integer flightID;
    private String clientName;
    private List<String> tourists;
    private String clientAddress;
    private int seats;

    public Integer getFlightID() {
        return flightID;
    }

    public String getClientName() {
        return clientName;
    }

    public List<String> getTourists() {
        return tourists;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public int getSeats() {
        return seats;
    }
}
