package Repository;

import Domain.Employee;

import java.util.Optional;

public interface EmployeeRepositoryInterface extends Repository<String, Employee> {

    /**
     *
     * @param name
     * @return
     */
    Optional<Employee> findEmployeeByName(String name);

}
