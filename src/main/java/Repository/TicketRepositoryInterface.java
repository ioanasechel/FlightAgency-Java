package Repository;

import Domain.Ticket;

import java.util.Optional;


public interface TicketRepositoryInterface extends Repository<Integer, Ticket> {

    /**
     *
     * @param flightID
     * @return
     */
    Optional<Ticket> findTicketsByFlightID(Integer flightID);


}
