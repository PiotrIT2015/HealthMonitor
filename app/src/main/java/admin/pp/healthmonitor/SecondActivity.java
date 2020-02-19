package admin.pp.healthmonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity{

    Button music, gallery, health;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        music=(Button)findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=YHc5SJlrJig"));
                startActivity(browsIntent);
            }
        });
        gallery=(Button)findViewById(R.id.Gallery);
        gallery.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent browsIntent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/folders/11QHxL2HeqolZqzlV_rkWNSB3HCYy6cjy"));
                startActivity(browsIntent2);
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

