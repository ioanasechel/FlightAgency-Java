package Repository;

import Domain.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightRepositoryInterface extends Repository<Integer, Flight> {

    /**
     *
     * @param airport
     * @return
     */
    List<Flight> findFlightsByAirport(String airport);


}
