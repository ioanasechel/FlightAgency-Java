package Domain;

import java.time.LocalDateTime;

public class Flight extends Entity<Integer>{
    private String destination;
    private LocalDateTime departure_time;
    private String airport;
    private int available_seats;

    public Flight(String destination, LocalDateTime departure_time, String airport, int available_seats) {
        this.destination = destination;
        this.departure_time = departure_time;
        this.airport = airport;
        this.available_seats = available_seats;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDeparture_time() {
        return departure_time;
    }

    public String getAirport() {
        return airport;
    }

    public int getAvailable_seats() {
        return available_seats;
    }
}

