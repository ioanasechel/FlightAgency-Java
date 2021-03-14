package Domain;

public class Employee extends Entity<String>{
    private String password;
    private String name;

    public Employee(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + getId() + '\'' +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
