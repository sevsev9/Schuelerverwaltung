package fxverwaltung;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.time.DateTimeException;

public class Controller {
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
            //DateTime date;


    public void search(ActionEvent actionEvent) {
    }
}
