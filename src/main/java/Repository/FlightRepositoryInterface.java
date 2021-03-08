package Repository;

import Domain.Flight;

import java.util.Optional;

public interface FlightRepositoryInterface extends Repository<Integer, Flight> {

    /**
     *
     * @param airport
     * @return
     */
    Optional<Flight> findFlightsByAirport(String airport);


}
