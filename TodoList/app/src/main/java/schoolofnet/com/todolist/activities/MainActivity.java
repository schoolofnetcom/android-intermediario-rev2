package schoolofnet.com.todolist.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import schoolofnet.com.todolist.R;
import schoolofnet.com.todolist.db.CRUDHelper;
import schoolofnet.com.todolist.db.CrudHelperImpl;
import schoolofnet.com.todolist.db.DbHandler;
import schoolofnet.com.todolist.entities.Todo;
import schoolofnet.com.todolist.views.TodoListAdapter;

public class MainActivity extends AppCompatActivity {

    private List<Todo> todos = new ArrayList<Todo>();
    private TodoListAdapter todoAdapter;
    private DbHandler handler = null;
    private CRUDHelper db;


    @BindView(R.id.fab_new_task)
    FloatingActionButton btnFabNewTask;
    @BindView(R.id.todo_list)
    RecyclerView todoListrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.handler = new DbHandler(this);
        this.db = new CrudHelperImpl(this.handler);

        ButterKnife.bind(this);
        populateRecyclerView();
    }

    @OnClick(R.id.fab_new_task)
    public void onClick(View view) {
        Intent addTask = new Intent(this, AddActivity.class);
        startActivity(addTask);
    }

    private void populateRecyclerView() {
        todos = db.findAll();
        todoAdapter = new TodoListAdapter(this, todos);
        todoListrv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        todoListrv.setItemAnimator(new DefaultItemAnimator());
        todoListrv.setAdapter(todoAdapter);
        setItemAnimation();
    }

    private void setItemAnimation() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView view, RecyclerView.ViewHolder holder, RecyclerView.ViewHolder target){
              return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder view, int dir) {
                deleteTodo(view.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(todoListrv);
    }

    public void deleteTodo(final int pos) {
        new AlertDialog.Builder(this)
                .setTitle("TodoList")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int btn) {
                        dialog.dismiss();
                        deleteTodoFromDB(pos);
                    }
                })
                .setNegativeButton("No", (dialog, btn) -> dialog.dismiss())
                .show();
    }

    private void deleteTodoFromDB(int pos) {
        Todo todo = todos.get(pos);
        this.db.delete(todo);
        todos.remove(pos);
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        populateRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
