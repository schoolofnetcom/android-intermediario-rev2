package schoolofnet.com.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(this);

        Intent intent = getIntent();
        Integer a = intent.getIntExtra("KEY", 50);

        Toast.makeText(this, a.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        Intent secondActivity = new Intent(this, SecondActivity.class);
        startActivity(secondActivity);
    }
}
