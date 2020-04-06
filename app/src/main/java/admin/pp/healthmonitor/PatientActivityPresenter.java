package admin.pp.healthmonitor;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import android.widget.Button;

public class PatientActivityPresenter implements PatientActivity.Presenter{

    Button name, surname, historia;

    private PatientActivity.View view;
    private PatientActivity.Model model;
    public PatientActivityPresenter(PatientActivity.Model model) {
        this.model = model;
    }

    public void setView(PatientActivity.View view) {
        this.view = view;
    }

    public void RegisterButtonClicked() {
        if (view != null) {
            if (view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")) {
                System.out.println("Write CSV file:");
                Print(writeCsvFile(name.getText().toString(), surname.getText().toString(), historia.getText().toString()));

            } else {
                model.createPatient(view.getFirstName(), view.getLastName());
                System.out.println("Write CSV file:");
                Print(writeCsvFile(name.getText().toString(), surname.getText().toString(), historia.getText().toString()));

            }
        }
    }

    public void getCurrentUser() {
        User user = model.getPatient();
        if (user != null) {
            if (view != null) {
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
            }
        }
    }

    MySQL baza = new MySQL();
    private Socket client;
    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;
    private OutputStream outputStream;
    int port=0;

    public File writeCsvFile (String name2, String surname2, String historia2) throws NumberFormatException{

        //Delimiter used in CSV file
        final String COMMA_DELIMITER = ",";
        final String NEW_LINE_SEPARATOR = "\n";
        String pesel2=null;
        //CSV file header
        final String FILE_HEADER = "name,surname,pesel,historia";

        Patient kartoteka1 = new Patient(name2, surname2, Integer.parseInt(pesel2), historia2);


        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        FileWriter writer = null;
        File file=null;
        file = new File(exportDir, "Patient.csv");

        try {

            file.createNewFile();
            writer = new FileWriter(file);


            writer.append(FILE_HEADER.toString());


            writer.append(NEW_LINE_SEPARATOR);

            writer.append(kartoteka1.getFirstName());
            baza.insert(kartoteka1.getFirstName());
            writer.append(COMMA_DELIMITER);
            writer.append(kartoteka1.getLastName());
            baza.insert(kartoteka1.getLastName());
            writer.append(COMMA_DELIMITER);
            writer.append(kartoteka1.getHistory());
            baza.insert(kartoteka1.getHistory());
            writer.append(COMMA_DELIMITER);
            writer.append(String.valueOf(kartoteka1.getPesel()));
            baza.insert(String.valueOf(kartoteka1.getPesel()));
            writer.append(NEW_LINE_SEPARATOR);




            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }

        return file;
    }

    public void Print(File file){

        //create file instance
        //port=Integer.parseInt(etPort.getText().toString());
        // File file = new File("/mnt/sdcard/bluetooth/AnyFile.txt");
        try
        {

            //client = new Socket(etIp.getText().toString(), port);
            client = new Socket("127.0.0.1",8080);

            byte[] mybytearray = new byte[(int) file.length()]; //create a byte array to file

            fileInputStream = new FileInputStream((File)file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);

            bufferedInputStream.read(mybytearray, 0, mybytearray.length); //read the file

            outputStream = client.getOutputStream();

            outputStream.write(mybytearray, 0, mybytearray.length); //write file to the output stream byte by byte
            outputStream.flush();
            bufferedInputStream.close();
            outputStream.close();
            client.close();



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
