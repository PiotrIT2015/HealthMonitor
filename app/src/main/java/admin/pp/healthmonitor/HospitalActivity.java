package admin.pp.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Environment;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;



public class HospitalActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register;
    private EditText name, surname, pesel, historia;

    private Socket client;
    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;
    private OutputStream outputStream;
    int port=0;

    MySQL baza = new MySQL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init(){

        register = (Button)findViewById(R.id.register);
        name = (EditText)findViewById(R.id.name);
        surname = (EditText)findViewById(R.id.surname);
        pesel = (EditText)findViewById(R.id.pesel);
        historia = (EditText)findViewById(R.id.history);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        System.out.println("Write CSV file:");
        Print(writeCsvFile(name.getText().toString(), surname.getText().toString(),pesel.getText().toString(), historia.getText().toString()));


    }

    public File writeCsvFile (String name2, String surname2, String pesel2, String historia2) throws NumberFormatException{

        //Delimiter used in CSV file
        final String COMMA_DELIMITER = ",";
        final String NEW_LINE_SEPARATOR = "\n";

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

    void Print(File file){

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





