package fxverwaltung.IO;

import fxverwaltung.data.Student;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class file_IO {
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

    public void saveImg(ArrayList<Image> img) throws IOException {
        ArrayList<BufferedImage> bi = new ArrayList<>();

        for (Image i:img) {
            File outputFile = new File("./res/");
            BufferedImage bImage = SwingFXUtils.fromFXImage(i, null);
            try {
                ImageIO.write(bImage, "png", outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Image> loadImg() throws IOException, ClassNotFoundException {
            ArrayList<BufferedImage> bi = new ArrayList<>();
            ArrayList<Image> img = new ArrayList<>();

            try (
                    ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("svw_img.bin")))
            ){
                in.defaultReadObject();
                final int imageCount = in.readInt();
                bi = new ArrayList<BufferedImage>(imageCount);
                for (int i=0; i<imageCount; i++) {
                    bi.add(ImageIO.read(in));
                }
            }

            for (BufferedImage i:bi) {
                Image imag = SwingFXUtils.toFXImage(i,null);
                img.add(imag);
            }

        return img;
    }

    public static void saveToFile(Image image) {
        File outputFile = new File("./res/");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
