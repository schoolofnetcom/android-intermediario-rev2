package schoolofnet.com.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CrudHelperImpl implements CRUDHelper<Test> {

    private DbHandler handler = null;

    public CrudHelperImpl(DbHandler handler) {
        this.handler = handler;
    }

    @Override
    public void create(Test klazz) {
        SQLiteDatabase db = this.handler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", klazz.getName());

        db.insert("table_test", null, values);
        db.close();
    }

    @Override
    public Test findById(int id) {
        SQLiteDatabase db = this.handler.getReadableDatabase();

        Cursor cursor = db.query("table_test", new String[] { "id", "name" }
                , "id = ?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Test test = new Test(cursor.getInt(0), cursor.getString(1));

        return test;
    }

    @Override
    public List<Test> findAll() {
        List<Test> list = new ArrayList<Test>();

        SQLiteDatabase db = this.handler.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM table_test", null);

        if (cursor.moveToFirst()) {
            do {
                Test test = new Test(cursor.getInt(0), cursor.getString(1));
                list.add(test);
            } while (cursor.moveToNext());
        }

        return list;
    }

    @Override
    public int update(Test klazz) {
        SQLiteDatabase db = this.handler.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", klazz.getName());

        return db.update("table_test", values, " id = ?", new String[] { String.valueOf(klazz.getId()) });
    }

    @Override
    public void delete(Test klazz) {
        SQLiteDatabase db = this.handler.getWritableDatabase();

        db.delete("table_test", " id = ?", new String[] { String.valueOf(klazz.getId()) });
        db.close();
    }
}
