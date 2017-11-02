package schoolofnet.com.todolist.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import schoolofnet.com.todolist.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import schoolofnet.com.todolist.entities.Todo;

public class TodoViewerHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.todo_title)
    TextView txtTodoTitle;

    @BindView(R.id.todo_desc)
    TextView txtTodoDesc;

    @BindView(R.id.todo_id)
    TextView txtTodoId;


    public TodoViewerHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bindElements(Context ctx, Todo todo) {
        txtTodoId.setId(Integer.parseInt(todo.getId().toString()));
        txtTodoTitle.setText(todo.getTitle());
        txtTodoDesc.setText(todo.getDescription());
    }
}
