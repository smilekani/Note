package in.co.mani.kani.note;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ListView listview;
    SQLiteDatabase db;
    DbHelper dbHelper;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String sqlquery = "SELECT * FROM "+DbHelper.TABLE_NAME;
        int cursorCount =0;
        dbHelper = new DbHelper(this);
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlquery, null);
        cursor.moveToFirst();
        cursorCount = cursor.getCount();
        Log.d("Count", "onCreate: "+cursorCount);
        ArrayAdapter arrayAdapter = new ArrayAdapter<Note>(this, R.layout.activity_main, R.id.textview);
        for (int i = cursorCount - 1; i >= 0 ; i--) {
            cursor.moveToPosition(i);
            String _id = cursor.getString(0);
            String title = cursor.getString(1);
            String type = cursor.getString(2);
            String description = cursor.getString(3);
            String time = cursor.getString(4);
            String date = cursor.getString(5);
            Note note = new Note(_id, title, type, description, time, date);
            arrayAdapter.add(note);
        }
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = (Note) parent.getAdapter().getItem(position);
                Intent intent = new Intent(view.getContext(),ListNotes.class);
                Log.d("NOTE", "onItemClick: "+note);
                intent.putExtra("_id", note.get_id());
                intent.putExtra("title", note.getTitle());
                intent.putExtra("type", note.getType());
                intent.putExtra("description", note.getDescription());
                intent.putExtra("time", note.getTime());
                intent.putExtra("date", note.getDate());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                addSaveListener();
                return(true);
            case R.id.reset:
                textView =(TextView)findViewById(R.id.textview);
                textView.setText("Nothing is selected");
                return(true);
            case R.id.about:
                Toast.makeText(this, R.string.about_toast, Toast.LENGTH_LONG).show();
                return(true);
            case R.id.select:
                selectListener();
                return(true);
            case R.id.exit:
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    private void addSaveListener()
    {
        Intent intent = new Intent(getApplicationContext(),ListNotes.class);
        startActivity(intent);
    }

    private void selectListener()
    {
        Intent intent = new Intent(getApplicationContext(),SelectNote.class);
        startActivity(intent);
    }

}
