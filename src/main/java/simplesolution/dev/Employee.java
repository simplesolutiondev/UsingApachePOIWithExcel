package simplesolution.dev;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String address;
    private String city;
    private BigDecimal salary;

    public Employee() {

    }

    public static Employee createEmployee(int employeeId, String firstName, String lastName, int birthYear, int birthMonth, int birthDate, String address, String city, BigDecimal salary) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAddress(address);
        employee.setCity(city);
        employee.setSalary(salary);
        Calendar calendar = Calendar.getInstance();
        calendar.set(birthYear, birthMonth, birthDate);
        employee.setBirthDate(calendar.getTime());
        return employee;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
