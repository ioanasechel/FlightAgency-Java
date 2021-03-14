package Repository;

import Domain.Ticket;

import java.util.List;
import java.util.Optional;


public interface TicketRepositoryInterface extends Repository<Integer, Ticket> {

    /**
     *
     * @param flightID
     * @return
     */
    List<Ticket> findTicketsByFlightID(Integer flightID);


}
