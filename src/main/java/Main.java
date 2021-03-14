import Domain.Employee;
import Domain.Flight;
import Domain.Ticket;
import Repository.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties props=new Properties();
        Random rand = new Random();
        try {
            props.load(new FileReader("bd.properties"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.properties "+e);
        }

        // employees test
        EmployeeRepositoryInterface employeeRepo = new EmployeeDBRepository(props);
        Employee emp = new Employee("password6", "Employee name 6");
        emp.setId("employee6");
        //employeeRepo.add(emp);
        //employeeRepo.delete("employee1");
        System.out.println("All employees: ");
        for(Employee employee:employeeRepo.findAll())
            System.out.println(employee);

        //flights test
        FlightRepositoryInterface flightRepo = new FlightDBRepository(props);
        LocalDate date = LocalDate.of(2021, 8, 11);
        Flight f = new Flight("destination1", date, "airport1", 300);
        f.setId(rand.nextInt(1000));
        flightRepo.add(f);
        //flightRepo.delete(100);
        List<Flight> flights = flightRepo.findFlightsByAirport("airport1");
        System.out.println("All flights: ");
        for (Flight flight:flightRepo.findAll())
            System.out.println(flight);


        //tickets test
        TicketRepositoryInterface ticketRepo = new TicketDBRepository(props);
        Ticket t = new Ticket(2, "name1", "tourist1, tourist2", "address1", 2);
        t.setId(rand.nextInt(1000));
        ticketRepo.add(t);
        //ticketRepo.delete(100);
        List<Ticket> tickets = ticketRepo.findTicketsByFlightID(2);
        System.out.println("All tickets: ");
        for (Ticket ticket:tickets)
            System.out.println(ticket);


        System.out.println("All tests passed! :)");
    }
}
