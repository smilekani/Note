package in.co.mani.kani.note;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by zmxxkan on 8/9/2017.
 */

public class SelectNote extends AppCompatActivity {

    ListView listview;
    SQLiteDatabase db;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_notes);
        String sqlquery = "SELECT * FROM "+DbHelper.TABLE_NAME;
        int cursorCount =0;
        dbHelper = new DbHelper(this);
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlquery, null);
        cursor.moveToFirst();
        cursorCount = cursor.getCount();
        Log.d("Count", " "+cursorCount);
        ArrayAdapter arrayAdapter = new ArrayAdapter<Note>(this, R.layout.select_notes, R.id.selecttextview);
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
        listview = (ListView) findViewById(R.id.selectlistview);
        listview.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.delete:
                deleteListener();
                //Log.d("Item", "onOptionsItemSelected: "+item.getItemId());
                //Toast.makeText(this, R.string.about_toast, Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteListener()
    {
        Log.d("kani", "deleteListener: "+listview.getAdapter());
        /*int checkedItemCount = listview.getCount();
        for (int i = 0; i > checkedItemCount; i ++ )
        {
            Toast.makeText(this, R.string.save+" = "+i, Toast.LENGTH_LONG).show();
        }*/
        //long[] itemIds = listview.getCheckedItemIds();
        //Log.d("checkedItemCount Items",""+checkedItemCount);
        //Intent intent = new Intent(getApplicationContext(),DeleteNote.class);
        //startActivity(intent);
    }
}
