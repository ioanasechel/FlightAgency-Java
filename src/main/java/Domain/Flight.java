package Domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Flight extends Entity<Integer>{
    private String destination;
    private LocalDate departure_date;
    private String airport;
    private int available_seats;

    public Flight(String destination, LocalDate departure_date, String airport, int available_seats) {
        this.destination = destination;
        this.departure_date = departure_date;
        this.airport = airport;
        this.available_seats = available_seats;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getDeparture_date() {
        return departure_date;
    }

    public String getAirport() {
        return airport;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + getId() + '\'' +
                "destination='" + destination + '\'' +
                ", departure_date=" + departure_date +
                ", airport='" + airport + '\'' +
                ", available_seats=" + available_seats +
                '}';
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }
}

