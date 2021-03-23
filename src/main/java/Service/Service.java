package Service;


import Domain.Employee;
import Domain.Flight;
import Domain.Ticket;
import Repository.EmployeeRepositoryInterface;
import Repository.FlightRepositoryInterface;
import Repository.TicketRepositoryInterface;

import java.util.List;

public class Service {
    private EmployeeRepositoryInterface repoEmployee;
    private FlightRepositoryInterface repoFlights;
    private TicketRepositoryInterface repoTickets;

    public Service(EmployeeRepositoryInterface repoEmployee, FlightRepositoryInterface repoFlights, TicketRepositoryInterface repoTickets) {
        this.repoEmployee = repoEmployee;
        this.repoFlights = repoFlights;
        this.repoTickets = repoTickets;
    }

    public Iterable<Flight> getAllFlights() {
        return repoFlights.findAll();
    }

    public Employee getOneEmployee(String text) {
        return repoEmployee.findOne(text);
    }

    public Flight getOneFlight(Integer id) {
        return repoFlights.findOne(id);
    }

    public void addTicket(Ticket ticket) {
        repoTickets.add(ticket);
        Flight newFlight= repoFlights.findOne(ticket.getFlightID());
        newFlight.setAvailable_seats(newFlight.getAvailable_seats()-ticket.getSeats());
        repoFlights.update(newFlight);
    }
}
