package in.ac.iiitmanipur.calc;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import in.ac.iiitmanipur.calc.db.TaskContract;
        import in.ac.iiitmanipur.calc.db.TaskDbHelper;

        import in.ac.iiitmanipur.calc.R;

import static android.R.id.list;
import static in.ac.iiitmanipur.calc.R.id.view;

public class security extends AppCompatActivity {
   // private static final String TAG = "MainActivity";
    private TaskDbHelper mHelper;
    private ListView mTaskListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);


        mHelper = new TaskDbHelper(this);
        mTaskListView = (ListView) findViewById(R.id.list_todo);
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                taskEditText.setHint("username & Password");
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put(TaskContract.TaskEntry.COL_TASK_TITLE, task);
                                db.insertWithOnConflict(TaskContract.TaskEntry.TABLE_NAME,
                                        null,
                                        values,
                                        SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                                updateUI();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

            case R.id.password:
                final EditText passEditText = new EditText(this);
                passEditText.setHint("new password");
                AlertDialog dialog_pass = new AlertDialog.Builder(this)
                        .setTitle("Change password")
                        .setMessage("use 0-9 only")
                        .setView(passEditText)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDatabase db = mHelper.getWritableDatabase();
                                String task="1234";
                                Cursor cursor = db.query(TaskContract.TaskEntry.TABLE_NAME, new String[]{TaskContract.TaskEntry._ID,
                                        TaskContract.TaskEntry.COL_PASS},null, null, null, null, null);

                                if(cursor.moveToFirst()){
                                task = cursor.getString(1);
                                }
                                cursor.close();
                                db.delete(TaskContract.TaskEntry.TABLE_NAME, TaskContract.TaskEntry.COL_TASK_TITLE + " = ?", new String[]{task});
                                db.close();
                                String pass = String.valueOf(passEditText.getText());
                                ContentValues values = new ContentValues();
                                values.put(TaskContract.TaskEntry.COL_PASS, pass);
                                db.insertWithOnConflict(TaskContract.TaskEntry.TABLE_NAME,
                                        null,
                                        values,
                                        SQLiteDatabase.CONFLICT_REPLACE);
                                db.close();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                       .create();
                dialog_pass.show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(TaskContract.TaskEntry.TABLE_NAME, TaskContract.TaskEntry.COL_TASK_TITLE + " = ?", new String[]{task});
        db.close();
        updateUI();
    }

    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(TaskContract.TaskEntry.TABLE_NAME,
                new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE);
            taskList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.item_todo,
                    R.id.task_title,
                    taskList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
        cursor.close();
        db.close();
    }

    // Create copyable TextView

    public void copy(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("To copy, Press long on the text.");
        final EditText input = new EditText(this);
        alert.setView(input);
        //for getting string of current text view
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        input.setText(task);
        alert.show();
    }
}