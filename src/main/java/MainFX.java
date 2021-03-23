import Controller.LogInController;
import Domain.Employee;
import Domain.Flight;
import Domain.Ticket;
import Repository.*;
import Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class MainFX extends Application{

    EmployeeDBRepository employeeRepo;
    FlightDBRepository flightRepo;
    TicketDBRepository ticketRepo;
    Service service;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Properties props=new Properties();
        Random rand = new Random();
        try {
            props.load(new FileReader("bd.properties"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.properties "+e);
        }

        employeeRepo = new EmployeeDBRepository(props);
        flightRepo = new FlightDBRepository(props);
        ticketRepo = new TicketDBRepository(props);

        service = new Service(employeeRepo, flightRepo, ticketRepo);

        // employees test
//        Employee emp = new Employee("password6", "Employee name 6");
//        emp.setId("employee6");
//        //employeeRepo.add(emp);
//        //employeeRepo.delete("employee1");
//        System.out.println("All employees: ");
//        for(Employee employee:employeeRepo.findAll())
//            System.out.println(employee);
//        System.out.println(employeeRepo.findOne("employee2"));

        //flights test
//        LocalDate date = LocalDate.of(2021, 8, 11);
//        Flight f = new Flight("destination1", date, "airport1", 300);
//        f.setId(rand.nextInt(1000));
        //flightRepo.add(f);
        //flightRepo.delete(100);
//        List<Flight> flights = flightRepo.findFlightsByAirport("airport1");
//        System.out.println("All flights: ");
//        for (Flight flight:flightRepo.findAll())
//            System.out.println(flight);


        //tickets test
        //Ticket t = new Ticket(2, "name1", "tourist1, tourist2", "address1", 2);
        //t.setId(rand.nextInt(1000));
        //ticketRepo.add(t);
        //ticketRepo.delete(100);
//        List<Ticket> tickets = ticketRepo.findTicketsByFlightID(2);
//        System.out.println("All tickets: ");
//        for (Ticket ticket:tickets)
//            System.out.println(ticket);

        System.out.println("All tests passed! :)");

        initView(primaryStage);
        primaryStage.show();
    }

    private void initView(Stage primaryStage) throws Exception{
        //login
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/LogInPage.fxml"));
        AnchorPane layout = loader.load();
        primaryStage.setScene(new Scene(layout));
        primaryStage.setTitle("Flight Agency");
        primaryStage.getIcons().add(new Image("images/plane.png"));
        LogInController logInController = loader.getController();
        logInController.setService(service, primaryStage);
    }
}
