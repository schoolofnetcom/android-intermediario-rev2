package schoolofnet.com.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnGoBack = (Button) findViewById(R.id.btnGoBack);
        btnGoBack.setOnClickListener(this);

        DbHandler handler = new DbHandler(this);
        CrudHelperImpl db = new CrudHelperImpl(handler);

//        create
//        db.create(new Test("Leonan"));

//        findAll
//        List<Test> list = db.findAll();
//        for (Test tst: list) {
//            Toast.makeText(this, tst.getId() + " - " + tst.getName(), Toast.LENGTH_SHORT).show();
//        }
//        for(int i = 0; i < list.size(); i++) {
//            Toast.makeText(this, list.get(i).getId() + " - " + list.get(i).getName(), Toast.LENGTH_SHORT).show();
//        }

//        findbyId
//        Test test = db.findById(1);
//        Toast.makeText(this, test.getId() + " - " + test.getName(), Toast.LENGTH_SHORT).show();

//        update
//        Test test = db.findById(1);
//        test.setName("Leonan updated");
//        db.update(test);
//        test = db.findById(1);
//        Toast.makeText(this, test.getId() + " - " + test.getName(), Toast.LENGTH_SHORT).show();

//        delete
//        Test test = db.findById(1);
//        db.delete(test);
    }

    @Override
    public void onClick(View view) {
        Intent mainActivity = new Intent(this, MainActivity.class);

        mainActivity.putExtra("KEY", 10);

        startActivity(mainActivity);
    }
}
