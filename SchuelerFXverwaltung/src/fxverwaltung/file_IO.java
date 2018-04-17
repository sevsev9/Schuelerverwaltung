package fxverwaltung;

import javafx.scene.image.Image;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class file_IO {
    private File srcfile = null;
    private ArrayList<String> readsrc = new ArrayList<>();
    private String in = null;

    public file_IO() {}


    public Collection<Student> readCSV(String src) throws FileNotFoundException, IOException {
        Collection<Student> persons = new ArrayList<>();

        try (
                BufferedReader bf = new BufferedReader(new FileReader(src))
        ){
            while ((in = bf.readLine()) != null) {
                readsrc.add(in);
                persons.add(parseString(in));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            e.fillInStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            e.fillInStackTrace();
            throw e;
        }

        return persons;
    }

    public void writeCSV(Collection<Student> s, String path) throws FileNotFoundException, IOException{
        try (
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path)))
                ){
            pw.println("First Name, Last Name, Username, Email, School, Class, Date, Password");
            for (Student c:s) {
                pw.println(c.toCSV(','));
            }
        }


    }

    public void writeLocal(String path, Collection<Student> students)  throws FileNotFoundException, IOException{
        try (
                ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(path)))
                ){
            os.writeObject(students);
        }
    }

    public Collection<Student> readLocal(String path)  throws FileNotFoundException, IOException, ClassNotFoundException{
        try (
                ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)))
        ){
            return ((Collection<Student>) is.readObject());
        }
    }

    private Student parseString(String line) {
        String[] values = line.split("[,;]");
        return new Student(values[0], values[1], values[2], values[3], values[4], values[5], LocalDate.parse(values[6]),values[7]);
    }

    public Student parseLineScanner(String Line) {
        Scanner sc = new Scanner(Line);
        sc.useDelimiter("[,;]");
        return new Student(sc.next(),sc.next(),sc.next(),sc.next(), sc.next(), sc.next(), LocalDate.parse(sc.next()),sc.next());
    }

    public ArrayList<Image> loadImg() throws IOException, ClassNotFoundException{
        ArrayList<Image> img = new ArrayList<>();

        try (
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("svw_img.bin")))
                ){
                img = (ArrayList<Image>) ois.readObject();
        }

        return img;
    }

    public void saveImg(ArrayList<Image> img) throws IOException{
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("svw_img")))
                ){
                oos.writeObject(img);
        }
    }
}
