package fxverwaltung;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.TreeSet;

public class Main_ctr {
    public ListView student_table;
    public Label student_label;
    public ChoiceBox value_option;
    public TextField search_bar;
    public Button search_button;
    public ScrollBar student_scrollbar;
    public ImageView headerimg;
    public ImageView student_image;
    public TextArea student_data;
    public TextField opt_lname;
    public TextField opt_fname;
    public TextField opt_email;
    public TextField opt_uname;
    public TextField opt_class;
    public TextField opt_school;
    public DatePicker opt_date;

    public Button add_btn;
    public PasswordField add_passwd;
    public TextField add_class;
    public TextField add_email;
    public TextField add_school;
    public TextField add_lname;
    public TextField add_fname;
    public DatePicker add_date;
    public TextField add_uname;
    public Button add_close;


    private Collection<Student> students = null;

    //Search Variables
        //Primary:
            String search; //valueOf(search_bar)
            String usage;  //valueOf(value_option)
        //Optional:
            String email;
            String fname;
            String lname;
            String uname;
            String class_;
            String school;
            LocalDateTime date;
    ///////////////////////////////////////////////////


    public Main_ctr() {
        students = new TreeSet<>();
    }

    public void search(ActionEvent actionEvent) {

    }

    public void add_student(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("./addstudent.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Student");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add_btnaction(ActionEvent actionEvent) {
        Student s = new Student(add_uname.getText(),add_passwd.getText(),add_fname.getText(), add_lname.getText(), add_class.getText(), add_email.getText(), add_date.getValue());

        try {
            System.out.print(s.toString());
            students.add(s);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        Node src = (Node) actionEvent.getSource();
        Stage stage = (Stage) src.getScene().getWindow();
        stage.close();

    }
}
