package admin.pp.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.lang.Exception;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etCosts);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnPayment);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.toString(), Password.toString());
            }
        });

    }

    private void validate(String userName, String userPassword){

        if((userName.equals("Jan")) && (userPassword.equals("Kowalski"))){

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);

            try {
                String url = "127.0.0.1";
                String gmail = "https://store.steampowered.com/";

                Engine http = new Engine();

                // make sure cookies is turn on
                //CookieHandler.setDefault(new CookieManager());

                // 1. Send a "GET" request, so that you can extract the form's data.
                String page = http.GetPageContent(url);
                String postParams = http.getFormParams(page, "username@gmail.com", "password");

                // 2. Construct above post's content and then send a POST request for
                // authentication
                http.sendPost(url, postParams);

                // 3. success then go to gmail.
                String result = http.GetPageContent(gmail);
                System.out.println(result);
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }


}
