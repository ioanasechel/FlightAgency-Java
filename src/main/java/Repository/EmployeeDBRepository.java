package Repository;

import Domain.Employee;
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
import java.util.Optional;
import java.util.Properties;

public class EmployeeDBRepository implements EmployeeRepositoryInterface {

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public EmployeeDBRepository(Properties props) {
        logger.info("Initializing EmployeeDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public Employee add(Employee entity) {
        logger.traceEntry("saving task {} ", entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("insert into Employees (username, password, name) values (?,?,?)")){
            preStmt.setString(1, entity.getId());
            preStmt.setString(2, entity.getPassword());
            preStmt.setString(3, entity.getName());
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
    public Employee delete(String s) throws IOException {
        logger.traceEntry("deleting task with {}", s);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Employees where username=?")){
            preStmt.setString(1, s);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        logger.traceExit();
        return null;
    }

    @Override
    public Employee update(Employee entity) {
        return null;
    }

    @Override
    public Iterable<Employee> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Employee> employees = new ArrayList<>();
        try(PreparedStatement preStmt = con.prepareStatement("select * from Employees")) {
            try(ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    String username = result.getString("username");
                    String password =  result.getString("password");
                    String name = result.getString("name");
                    Employee employee = new Employee(password, name);
                    employee.setId(username);
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        logger.traceExit(employees);
        return employees;
    }

}
