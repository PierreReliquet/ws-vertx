package fr.pr.vertx.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    String firstName;
    String lastName;
    Date birthday;

    public Person(String firstName, String lastName, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public Person(String firstName, String lastName, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        try {
            this.birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        }catch (ParseException e) {
            e.printStackTrace();
            this.birthday = new Date();
        }
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String manualSerialization() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return firstName + " " + lastName + " - " + format.format(birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!birthday.equals(person.birthday)) return false;
        if (!firstName.equals(person.firstName)) return false;
        if (!lastName.equals(person.lastName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthday.hashCode();
        return result;
    }
}
