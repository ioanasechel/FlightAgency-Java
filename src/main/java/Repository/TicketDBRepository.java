package Repository;


import Domain.Ticket;
import Utils.JdbcUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TicketDBRepository implements TicketRepositoryInterface {

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public TicketDBRepository(Properties props) {
        logger.info("Initializing TicketDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }
    @Override
    public List<Ticket> findTicketsByFlightID(Integer flightIDN) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Ticket> tickets = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Tickets where flightID = ?")) {
            preStmt.setInt(1, flightIDN);
            try(ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int ticketID = result.getInt("ticketID");
                    int flightID = result.getInt("flightID");
                    String client_name = result.getString("clientName");
                    String tourists =  result.getString("tourists");
                    String client_address = result.getString("clientAddress");
                    int seats = result.getInt("seats");
                    Ticket ticket = new Ticket(flightID, client_name, tourists, client_address, seats);
                    ticket.setId(ticketID);
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        logger.traceExit(tickets);
        return tickets;
    }

    @Override
    public Iterable<Ticket> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Ticket> tickets = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Tickets")) {
            try(ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int ticketID = result.getInt("ticketID");
                    int flightID = result.getInt("flightID");
                    String client_name = result.getString("clientName");
                    String tourists =  result.getString("tourists");
                    String client_address = result.getString("clientAddress");
                    int seats = result.getInt("seats");
                    Ticket ticket = new Ticket(flightID, client_name, tourists, client_address, seats);
                    ticket.setId(ticketID);
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        logger.traceExit(tickets);
        return tickets;
    }

    @Override
    public Ticket add(Ticket entity) {

        logger.traceEntry("saving task {} ", entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into Tickets values (?,?,?,?,?,?)")){
            preStmt.setInt(1, entity.getId());
            preStmt.setInt(2, entity.getFlightID());
            preStmt.setString(3, entity.getClientName());
            preStmt.setString(4, entity.getTourists());
            preStmt.setString(5, entity.getClientAddress());
            preStmt.setInt(6, entity.getSeats());
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
    public Ticket delete(Integer integer) throws IOException {
        logger.traceEntry("deleting task with {}", integer);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Tickets where ticketID=?")){
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
    public Ticket update(Ticket entity) {
        return null;
    }

}
