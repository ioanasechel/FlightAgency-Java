package Domain;

import java.util.List;

public class Ticket extends Entity<Integer>{

    private Integer flightID;
    private String clientName;
    private String tourists;
    private String clientAddress;
    private int seats;

    public Ticket(Integer flightID, String clientName, String tourists, String clientAddress, int seats) {
        this.flightID = flightID;
        this.clientName = clientName;
        this.tourists = tourists;
        this.clientAddress = clientAddress;
        this.seats = seats;
    }

    public Integer getFlightID() {
        return flightID;
    }

    public String getClientName() {
        return clientName;
    }

    public String getTourists() {
        return tourists;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ID=" + getId() + '\'' +
                "flightID=" + flightID +
                ", clientName='" + clientName + '\'' +
                ", tourists='" + tourists + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", seats=" + seats +
                '}';
    }
}
