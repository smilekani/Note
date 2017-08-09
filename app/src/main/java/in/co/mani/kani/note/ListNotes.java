package in.co.mani.kani.note;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by zmxxkan on 8/2/2017.
 */

public class ListNotes extends Activity {

    SQLiteDatabase db;
    DbHelper dbHelper;
    EditText editTitle;
    EditText editMsg;
    String _id;
    Button saveBtn;
    Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_notes);
        editTitle = (EditText) findViewById(R.id.title_input);
        editMsg = (EditText) findViewById(R.id.msg_input);
        _id = getIntent().getStringExtra("_id");
        String title = getIntent().getStringExtra("title");
        String type = getIntent().getStringExtra("type");
        String description = getIntent().getStringExtra("description");
        String time = getIntent().getStringExtra("time");
        String date = getIntent().getStringExtra("date");
        editTitle.setText(title);
        editMsg.setText(type);
        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();
        addOrUpdateListener();
    }

    private void addOrUpdateListener()
    {
        saveBtn = (Button) findViewById(R.id.saveBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText titleText = (EditText) findViewById(R.id.title_input);
                EditText msgText = (EditText) findViewById(R.id.msg_input);
                String titleStr = titleText.getText().toString();
                String msgStr = msgText.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put(dbHelper.TITLE, titleStr);
                cv.put(dbHelper.DETAIL, msgStr);
                Log.d("Add or Update", "onClick: "+_id);
                if(_id != null)
                    db.update(dbHelper.TABLE_NAME,cv, "_id="+_id, null);
                else
                    db.insert(dbHelper.TABLE_NAME,null,cv);
                db.close();
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), R.string.save, Toast.LENGTH_LONG).show();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), R.string.save, Toast.LENGTH_LONG).show();
            }
        });
    }
}
