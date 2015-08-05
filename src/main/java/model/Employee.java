package model;

import javax.persistence.*;

/**
 * Created by Dawid on 2015-07-16.
 */

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "age")
    private Integer age;
    @Column(name = "telephone_number")
    private String telephoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "salary")
    private Integer salary;
    @Embedded
    private EmployeeAdress adress;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Gender gender, int age, String telephoneNumber, String email, Integer salary, EmployeeAdress adress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.salary = salary;
        this.adress = adress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public EmployeeAdress getAdress() {
        return adress;
    }

    public void setAdress(EmployeeAdress adress) {
        this.adress = adress;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
