package fxverwaltung;

import javafx.scene.image.Image;

public class Student {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String class_;
    private String email;
    private Image profilePic;

    public Student(String username, String password, String first_name, String last_name, String class_, String email) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.class_ = class_;
        this.email = email;
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
        return  "-----------------------------\n" +
                "\n Username   =" + username +
                "\n First Name =" + first_name +
                "\n Last Name  =" + last_name  +
                "\n Class      =" + class_ +
                "\n E-mail     =" + email +
                "-----------------------------\n";
    }
}
