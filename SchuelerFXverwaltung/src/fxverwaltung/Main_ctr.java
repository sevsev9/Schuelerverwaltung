package fxverwaltung;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InvalidClassException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

public class Main_ctr {
    public ListView student_table;
    public Label student_label;
    public ChoiceBox value_option;
    public TextField search_bar;
    public DatePicker date_bar;
    public Button search_button;
    public ScrollBar student_scrollbar;
    public ImageView headerimg;
    public ImageView student_image;
    public TextArea student_data;
    public Button student_add;
    public Button ch_save;
    public Button ch_btn_pwd;
    public Button ch_setImg;
    public Label pwd_fname;
    public Label pwd_lname;
    public Label pwd_mail;
    public Label pwd_uname;
    public Label pwd_school;
    public Label pwd_class;
    public Label pwd_date;
    public Label img_fname;
    public Label img_lname;
    public Label img_mail;
    public Label img_uname;
    public Label img_school;
    public Label img_class;
    public Label img_date;
    private Image SampleStudent = new Image(Main.class.getResourceAsStream("sample_student.png"));

    //Menu Items
    public MenuItem menu_overwritefromCSV;
    public MenuItem menu_appendfromCSV;
    public MenuItem menu_savelocal;
    public MenuItem menu_savetocsv;
    public MenuItem btn_about;

    //Edit Fields
    public TextField ch_fname;
    public TextField ch_lname;
    public TextField ch_uname;
    public TextField ch_email;
    public TextField ch_class;
    public DatePicker ch_date;
    public TextField ch_school;

    //Optional search fields
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

    private static ObservableList<Student> osl = FXCollections.observableArrayList();
    private static Collection<Student> students = new TreeSet<>();
    private ArrayList<Image> img; //Sample = 0 ... Max Mustermann = 1
    private int imgctr = 0;
    private Student curr_std = null;

    private file_IO file_io = new file_IO();


    private ChangeListener<Number> voCL = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (newValue.intValue() == 6){
                date_bar.setVisible(true);
                search_bar.setVisible(false);
                System.out.println("seawas");
            } else if (!search_bar.isVisible()){
                date_bar.setVisible(false);
                search_bar.setVisible(true);
            }
        }
    };

    public void init() {
        //Load Image array if available
        try {
            if ((img = file_io.loadImg()) != null){
                System.out.println("Loaded Image Array");

                for (Image i:img) {
                    imgctr++;
                }
            }
        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File damaged!");
            alert.setContentText("The file ./svw_img.bin appears to be damaged");
            alert.setHeaderText("Damaged File!");
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("The file \"./svw_img.bin\" not found!");
            alert.setHeaderText("File not found!");
        }

        if (img == null) {
             img = new ArrayList<>();
            System.out.println(" No Image array found generating default array");
            //Image Ressources
            Image max = new Image(Main.class.getResourceAsStream("max.jpg"));
            img.add(SampleStudent);
            img.add(max);
        }


        value_option.setItems(FXCollections.observableArrayList("First Name", "Last Name", "Username", "E-Mail", "School", "Class", "Date of Birth"));
        value_option.getSelectionModel().selectedIndexProperty().addListener(voCL);
        value_option.setTooltip(new Tooltip("Select Value to Search for"));

        date_bar.setMaxWidth(search_bar.getLayoutX());
        student_image.setImage(SampleStudent);
        //jaxrs!!!!

        //Load Contents if available
        try {
            students.addAll(file_io.readLocal("./seas.bin"));
            osl.addAll(students);
            System.out.println(students);
        } catch (InvalidClassException e) {
            System.out.println("Invalid Class (File not Found)");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (students.isEmpty()) {
            //Max Mustermann
            Student maxmustermann = new Student("Max", "Mustermann", "Sample", "example@email.com", "Klasse", "Schule", null, "password");
            maxmustermann.setImg(1);

            osl.add(maxmustermann);
            students.add(maxmustermann);
        }

        student_table.setItems(osl);
        student_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Student s = (Student) newValue;
                curr_std = s;
                ch_fname.setText(s.getFirst_name());
                ch_lname.setText(s.getLast_name());
                ch_uname.setText(s.getUsername());
                ch_email.setText(s.getEmail());
                ch_school.setText(s.getSchool());
                ch_class.setText(s.getClass_());
                ch_date.setValue(LocalDate.of(1999,1,1));

                if (img.get(s.getImg()) != null){
                    student_image.setImage(img.get(s.getImg()));
                } else {
                    student_image.setImage(SampleStudent);
                }

            }
        });

        for (Image i:img) {
            imgctr++;
        }
    }

    public void check4select(ActionEvent actionEvent) {
        int i = student_table.getSelectionModel().getSelectedIndex();
        System.out.println(i);

    }


    public void search(ActionEvent actionEvent) {

        //Search Variables
            //Value Array:
                ArrayList<String> val = new ArrayList<>();
                    /*
                    * 1. First Name
                    * 2. Last Name
                    * 3. Username
                    * 4. E-Mail
                    * 5. School
                    * 6. Class
                    * 7. Date of birth */
            //Primary:
                String search; //valueOf(search_bar)
                String usage;  //valueOf(value_option)
                    /* Refer to val explanation */
        ///////////////////////////////////////////////////

            if (opt_fname != null) { val.add(opt_fname.getText());} else {val.add("0");}
            if (opt_lname != null) { val.add(opt_lname.getText()); } else {val.add("0");}
            if (opt_uname != null) { val.add(opt_uname.getText()); } else {val.add("0");}
            if (opt_email != null) { val.add(opt_email.getText()); } else {val.add("0");}
            if (opt_school != null) { val.add(opt_school.getText()); } else {val.add("0");}
            if (opt_class != null) { val.add(opt_class.getText()); } else {val.add("0");}
            if (opt_date.getValue() != null) {  val.add(opt_date.getValue().toString()); } else {val.add("0");}

            for (int i = 0; i<7; i++) {
                for (Student s: students) {
                    int mctr = 0; //Match Counter
                    int nlctr = 7; //Null Counter
                    double percentage = 0;      //Calculate Percentage of match

                    if (!val.get(0).equals("0")) {
                        if (s.getFirst_name().equals(val.get(0))) {
                            mctr++;
                            nlctr--;
                        }
                    }
                    if (!val.get(1).equals("0")) {
                        if (s.getLast_name().equals(val.get(1))){
                            mctr++;
                            nlctr--;
                        }
                    }
                    if (!val.get(2).equals("0")) {
                        if (s.getUsername().equals(val.get(2))){
                            mctr++;
                            nlctr--;
                        }
                    }
                    if (!val.get(3).equals("0")) {
                        if (s.getEmail().equals(val.get(3))){
                            mctr++;
                            nlctr--;
                        }
                    }
                    if (!val.get(4).equals("0")) {
                        if (s.getSchool().equals(val.get(3))){
                            mctr++;
                            nlctr--;
                        }
                    }
                    if (!val.get(5).equals("0")) {
                        if (s.getClass_().equals(val.get(3))){
                            mctr++;
                            nlctr--;
                        }
                    }
                    if (!val.get(6).equals("0")) {
                        if (s.getDate().equals(LocalDate.parse(val.get(3)))){
                            mctr++;
                            nlctr--;
                        }
                    }

                }
            }

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
            System.exit(0);
        }
    }

    public void add_btnaction(ActionEvent actionEvent) {
        if (    add_fname.getText() == null ||
                add_lname.getText() == null ||
                add_uname.getText() == null ||
                add_email.getText() == null ||
                add_class.getText() == null ||
                add_school.getText() == null ||
                add_date.getValue() == null ||
                add_passwd.getText() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please insert a value!");
            alert.setContentText("Values can't be null");
            alert.showAndWait();
            return;
        }

        Student s = new Student(add_fname.getText(), add_lname.getText(), add_uname.getText(), add_email.getText(), add_class.getText(), add_school.getText(), add_date.getValue(), add_passwd.getText());

        students.add(s);
        osl.add(s);

        Node src = (Node) actionEvent.getSource();
        Stage stage = (Stage) src.getScene().getWindow();
        stage.close();  //closes window
    }

    public void setStudent_image (Image image){
        this.student_image.setImage(image);
    }

    @Deprecated
    public void refresh_list() {
        student_table.setItems(osl);
    }

    public void savetoCSV() {

    }

    public void savelocal() {
        try {
            file_io.writeLocal("./seas.bin",students);
            file_io.saveImg(img);
            System.out.println("saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openabout() {

    }

    public void read_overwrite_CSV(ActionEvent actionEvent) {

    }

    public void read_append_CSV(ActionEvent actionEvent) {
    }

    public void ch_save(ActionEvent actionEvent) {

    }

    public void ch_pass(ActionEvent actionEvent) {
        if (curr_std != null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("./addstudent.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Set Student Image");
                stage.setScene(new Scene(root));
                stage.show();

                pwd_fname.setText(curr_std.getFirst_name());
                pwd_lname.setText(curr_std.getLast_name());
                pwd_mail.setText(curr_std.getEmail());
                pwd_uname.setText(curr_std.getUsername());
                pwd_school.setText(curr_std.getSchool());
                pwd_class.setText(curr_std.getClass_());
                pwd_date.setText(curr_std.getDate().toString());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Student selected!");
            alert.setContentText("Please select a Student.");
            alert.showAndWait();
        }
    }

    public void setCurrentImg(ActionEvent actionEvent) {
        if (curr_std != null) {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            try {
                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                File file = fileChooser.showOpenDialog(null);

                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);

                img.add(image);
                imgctr++;
                curr_std.setImg(imgctr);
            } catch (IOException e){
                e.printStackTrace();
                System.exit(0);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Student selected!");
            alert.setContentText("Please select a Student.");
            alert.showAndWait();
        }
    }
}
