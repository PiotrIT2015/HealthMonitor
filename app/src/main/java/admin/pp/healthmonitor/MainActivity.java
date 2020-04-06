package admin.pp.healthmonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.lang.Exception;

//import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements LoginActivity.View {

    LoginActivity.Presenter presenter;
    private EditText name;
    private EditText password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //((App) getApplication()).getComponent().inject(this);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.btnPayment);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginButtonClicked();
                Intent myIntent = new Intent(MainActivity.this,  SecondActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
    /*
    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrentUser();
    }*/
    @Override
    public String getFirstName() {
        return name.getText().toString();
    }
    @Override
    public String getLastName() {
        return password.getText().toString();
    }
    @Override
    public void showInputError() {
        Toast.makeText(this, "First Name or last name cannot be empty", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void setFirstName(String firstName) {
        this.name.setText(firstName);
    }
    @Override
    public void setLastName(String password) {
        this.password.setText(password);
    }
    @Override
    public void showUserSavedMessage() {
        Toast.makeText(this, "User saved successfully", Toast.LENGTH_SHORT).show();
    }
}