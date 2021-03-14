package Repository;

import Domain.Employee;
import Domain.Flight;
import Utils.JdbcUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class FlightDBRepository implements FlightRepositoryInterface{

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public FlightDBRepository(Properties props) {
        logger.info("Initializing FlightDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public List<Flight> findFlightsByAirport(String airportN) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Flight> flights = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Flights where airport = ?")) {
            preStmt.setString(1, airportN);
            try(ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int flightID = result.getInt("flightID");
                    String destination = result.getString("destination");
                    String airport = result.getString("airport");
                    int available_seats = result.getInt("available_seats");
                    LocalDate departure_date = result.getDate("departure_date").toLocalDate();
                    Flight flight = new Flight(destination, departure_date, airport, available_seats);
                    flight.setId(flightID);
                    flights.add(flight);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        logger.traceExit(flights);
        return flights;
    }

    @Override
    public Iterable<Flight> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Flight> flights=new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Flights")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int flightID = result.getInt("flightID");
                    String destination = result.getString("destination");
                    String airport = result.getString("airport");
                    int available_seats = result.getInt("available_seats");
                    LocalDate departure_date = result.getDate("departure_date").toLocalDate();
                    Flight flight = new Flight(destination, departure_date, airport, available_seats);
                    flight.setId(flightID);
                    flights.add(flight);
                }
            }
        }catch (SQLException e){
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        logger.traceExit(flights);
        return flights;
    }

    @Override
    public Flight add(Flight entity) {

        logger.traceEntry("saving task {} ", entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into Flights values (?,?,?,?,?)")){
            preStmt.setInt(1, entity.getId());
            preStmt.setString(2, entity.getDestination());
            preStmt.setString(3, entity.getAirport());
            preStmt.setInt(4, entity.getAvailable_seats());
            preStmt.setDate(5, Date.valueOf(entity.getDeparture_date()));
            int result = preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
        return entity;
    }

    @Override
    public Flight delete(Integer integer) throws IOException {
        logger.traceEntry("deleting task with {}", integer);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Flights where flightID=?")){
            preStmt.setInt(1, integer);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
        return null;
    }

    @Override
    public Flight update(Flight entity) {
        return null;
    }

}
