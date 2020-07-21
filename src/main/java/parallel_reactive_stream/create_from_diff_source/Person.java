package parallel_reactive_stream.create_from_diff_source;

import java.util.Comparator;
import java.util.Date;

public class Person implements Comparable<Person> {

    private int id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private int salary;

    private double coefient;

    public Person(int id, String firstName, String lastName, Date birthDate, int salary, double coefient) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
        this.coefient = coefient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getCoefient() {
        return coefient;
    }

    public void setCoefient(double coefient) {
        this.coefient = coefient;
    }

    @Override
    public int compareTo(Person o) {
        int compareLastName = this.getLastName().compareTo(o.getLastName());
        if (compareLastName!=0){
            return compareLastName;
        }else {
            return this.getFirstName().compareTo(o.getFirstName());
        }

    }
}
