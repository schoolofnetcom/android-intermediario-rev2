package schoolofnet.com.todolist.activities;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import schoolofnet.com.todolist.R;
import schoolofnet.com.todolist.db.CRUDHelper;
import schoolofnet.com.todolist.db.CrudHelperImpl;
import schoolofnet.com.todolist.db.DbHandler;
import schoolofnet.com.todolist.entities.Todo;

public class AddActivity extends AppCompatActivity {

    private DbHandler handler = null;

    @BindView(R.id.btn_create_task)
    Button btnCreateTask;
    @BindView(R.id.txt_task_title)
    EditText txtTitle;
    @BindView(R.id.txt_task_desc)
    EditText txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.handler = new DbHandler(this);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_create_task)
    public void save(View view) {
        CRUDHelper db = new CrudHelperImpl(this.handler);

        try {
            db.create(new Todo(txtTitle.getText().toString(), txtDesc.getText().toString()));
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
