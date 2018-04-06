package fxverwaltung;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class file_IO {
    private String src = null;
    private File srcfile = null;
    private ArrayList<String> readsrc = new ArrayList<>();
    private String in = null;

    public file_IO(String src) {
        this.src = src;
    }

    public file_IO(File srcfile) {
        this.srcfile = srcfile;
    }


    public Collection<Student> readCSV() throws FileNotFoundException, IOException {
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

    public void writeCSV(Collection<Student> s) throws FileNotFoundException, IOException{

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
}
