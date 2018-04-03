package fxverwaltung;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Student implements Serializable, Comparable{
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String class_;
    private String email;
    private String school;
    private LocalDate date;
    private Image profilePic;



    public Student(String first_name, String last_name, String username, String email, String class_, String school, LocalDate date, String password) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.class_ = class_;
        this.email = email;
        this.date = date;
        this.school = school;

    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public String toString() {
        return  "-----------------------------" +
                "\n Username   = " + username +
                "\n First Name = " + first_name +
                "\n Last Name  = " + last_name  +
                "\n Class      = " + class_ +
                "\n E-mail     = " + email +
                "\n Date       = " + date +
                "\n-----------------------------\n";
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() != this.getClass()) {
            return -1;
        }
        Student s = (Student) o;
        return s.getFirst_name().compareToIgnoreCase(this.getFirst_name()) + s.getLast_name().compareToIgnoreCase(this.getLast_name()) + s.getEmail().compareToIgnoreCase(this.getEmail()) + s.getUsername().compareToIgnoreCase(this.getUsername()) + s.getSchool().compareToIgnoreCase(this.getSchool());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(username, student.username) &&
                Objects.equals(password, student.password) &&
                Objects.equals(first_name, student.first_name) &&
                Objects.equals(last_name, student.last_name) &&
                Objects.equals(class_, student.class_) &&
                Objects.equals(email, student.email) &&
                Objects.equals(profilePic, student.profilePic) &&
                Objects.equals(school, student.school);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, password, first_name, last_name, class_, email, profilePic, school);
    }
}
