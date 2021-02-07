package admin.pp.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Environment;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
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



public class HospitalActivity extends AppCompatActivity {

    private Button register, call;
    private EditText name, surname, pesel, historia;
    PatientActivity.Presenter presenter;

    private Socket client;
    private FileInputStream fileInputStream;
    private BufferedInputStream bufferedInputStream;
    private OutputStream outputStream;
    int port=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init(){

        call = (Button)findViewById(R.id.call);
        register = (Button)findViewById(R.id.register);
        name = (EditText)findViewById(R.id.name);
        surname = (EditText)findViewById(R.id.surname);
        pesel = (EditText)findViewById(R.id.pesel);
        historia = (EditText)findViewById(R.id.history);

        call.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone="+112;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {



            public void onClick(View view) {

                PackageManager pm=getPackageManager();
                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = name.getText().toString()+","+ surname.getText().toString()+","+ historia.getText().toString();

                    PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    //Check if package exists or not. If not then code
                    //in catch block will be called
                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (NameNotFoundException e) {
                    System.out.println( "WhatsApp not Installed");
                }

            }


        });

    }



    public String getFirstName() {
        return name.getText().toString();
    }

    public String getLastName() {
        return surname.getText().toString();
    }

    public String getPesel() {
        return pesel.getText().toString();
    }

    public String gethistory() {
        return historia.getText().toString();
    }

    public void showInputError() {
        Toast.makeText(this, "First Name or last name cannot be empty", Toast.LENGTH_SHORT).show();
    }

    public void setFirstName(String firstName) {
        this.name.setText(firstName);
    }

    public void setLastName(String password) {
        this.surname.setText(password);
    }

    public void setPesel(int pesel) {
        this.name.setText(pesel);
    }

    public void sethistory(String history) {
        this.surname.setText(history);
    }

    public void showUserSavedMessage() {
        Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show();
    }
}






