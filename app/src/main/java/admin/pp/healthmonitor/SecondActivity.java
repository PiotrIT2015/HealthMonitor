package admin.pp.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.net.URL;

public class SecondActivity extends AppCompatActivity{

    Button music, gallery, health;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    private void init(){
        music=(Button)findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                String connectionString =
                        "jdbc:sqlserver://sqlserverapp.database.windows.net:1433;"
                                + "database=database1;"
                                + "user=Gekon@sqlserverapp.database.windows.net;"
                                + "password=pass;"
                                + "encrypt=true;"
                                + "trustServerCertificate=false;"
                                + "hostNameInCertificate=*.database.windows.net;"
                                + "loginTimeout=30;";

                // Declare the JDBC objects.
                Connection connection = null;
                Statement statement = null;
                ResultSet resultSet = null;
                PreparedStatement prepsInsertProduct = null;

                try {
                    connection = DriverManager.getConnection(connectionString);

                    // Create and execute an SELECT SQL prepared statement.
                    String insertSql = "SELECT music FROM db";

                    prepsInsertProduct = connection.prepareStatement(
                            insertSql,
                            Statement.RETURN_GENERATED_KEYS);
                    prepsInsertProduct.execute();

                    // Retrieve the generated key from the insert.
                    resultSet = prepsInsertProduct.getGeneratedKeys();

                    // Print the ID of the inserted row.
                    while (resultSet.next()) {
                        Intent browsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(resultSet.getString(1)));
                        startActivity(browsIntent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // Close the connections after the data has been handled.
                    if (prepsInsertProduct != null) try {
                        prepsInsertProduct.close();
                    } catch (Exception e) {
                    }
                    if (resultSet != null) try {
                        resultSet.close();
                    } catch (Exception e) {
                    }
                    if (statement != null) try {
                        statement.close();
                    } catch (Exception e) {
                    }
                    if (connection != null) try {
                        connection.close();
                    } catch (Exception e) {
                    }

                }
            }
        });

        gallery=(Button)findViewById(R.id.Mems);
        gallery.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try{
                    String fileName = "mem.jpg";
                    String website = "http://tutorialspoint.com/java_dip/images/"+fileName;

                    System.out.println("Downloading File From: " + website);

                    URL url = new URL(website);
                    InputStream inputStream = url.openStream();
                    OutputStream outputStream = new FileOutputStream(fileName);
                    byte[] buffer = new byte[2048];

                    int length = 0;

                    while ((length = inputStream.read(buffer)) != -1) {
                        System.out.println("Buffer Read of length: " + length);
                        outputStream.write(buffer, 0, length);
                    }

                    inputStream.close();
                    outputStream.close();

                } catch(Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
        });
        health=(Button)findViewById(R.id.health);
        health.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(SecondActivity.this,  HospitalActivity.class);
                SecondActivity.this.startActivity(myIntent);
            }
        });
    }
    }

