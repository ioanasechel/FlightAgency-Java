package Controller;

import Domain.Flight;
import Domain.Ticket;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainController {

    @FXML
    private TableView<Flight> tableFlights;
    @FXML
    private TableColumn<Flight, String> destination;
    @FXML
    private TableColumn<Flight, String> airport;
    @FXML
    private TableColumn<Flight, Date> departure_date;
    @FXML
    private TableColumn<Flight, Integer> available_seats;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtTourists;
    @FXML
    private TextField txtSeats;


    Flight flight;
    Ticket ticket;
    Service service;
    Stage stage;
    Stage previousStage;

    ObservableList<Flight> flightsTableModel = FXCollections.observableArrayList();


    public void setService(Service service, Stage stage, Stage previousStage){

        this.service = service;
        this.stage=stage;
        this.previousStage=previousStage;
        initFlightsTableModel();
    }

    @FXML
    public void initialize() {
        initializeFlightsTable();
    }

    private void initializeFlightsTable() {
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        airport.setCellValueFactory(new PropertyValueFactory<>("airport"));
        departure_date.setCellValueFactory(new PropertyValueFactory<>("departure_date"));
        available_seats.setCellValueFactory(new PropertyValueFactory<>("available_seats"));
        tableFlights.setItems(flightsTableModel);
    }

    private void initFlightsTableModel(){
        Iterable<Flight> flights=service.getAllFlights();
        flights.forEach(x->{
            if(x.getAvailable_seats()>0)
                flightsTableModel.add(x);
        });
    }
    public void clearFields(){
        txtSeats.setText("");
        txtAddress.setText("");
        txtName.setText("");
        txtTourists.setText("");
    }

    @FXML
    private void handlePurchase() throws IOException{
        Flight selectedFlight = tableFlights.getSelectionModel().getSelectedItem();
        //System.out.println(selectedFlight);
        if(selectedFlight!=null){
            Flight flight = service.getOneFlight(selectedFlight.getId());
            Random rand = new Random();
            String clientName = txtName.getText();
            String clientAddress = txtAddress.getText();
            String tourists = txtTourists.getText();
            Integer seats = Integer.valueOf(txtSeats.getText());
            //System.out.println(flight);
            Ticket ticket = new Ticket(flight.getId(), clientName, tourists, clientAddress, seats);
            ticket.setId(rand.nextInt(2000));
            service.addTicket(ticket);
            clearFields();
        }
    }
    @FXML
    public void logOut() {
        stage.close();
        previousStage.show();
    }
}
