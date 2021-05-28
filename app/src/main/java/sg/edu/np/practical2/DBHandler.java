package sg.edu.np.practical2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(@Nullable Context context) {
        super(context, "user", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS (NAME TEXT, DESCRIPTION TEXT, ID INTEGER, FOLLOWED BOOL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        onCreate(db);

    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put("NAME", user.getName());
        values.put("DESCRIPTION", user.getDescription());
        values.put("ID", user.getId());
        values.put("FOLLOWED", user.isFollowed());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("USERS", null, values);
        db.close();

    }

    public ArrayList<User> getUsers(){
        SQLiteDatabase db = getWritableDatabase();
        //Cursor cursor = db.rawQuery("select * from users where name = \""+ name + "\"", null);
        Cursor cursor = db.rawQuery("select * from users", null);
        User u = null;
        ArrayList<User> list = new ArrayList<>();

        while(cursor.moveToNext()){
            u = new User();
            u.setName(cursor.getString(0));
            u.setDescription(cursor.getString(1));
            u.setId(cursor.getInt(2));
            if(cursor.getInt(3) == 1){
                u.setFollowed(true);
            }
            if(cursor.getInt(3) == 0)
            {
                u.setFollowed(false);
            }
            list.add(u);
        }

        cursor.close();
        db.close();
        return list;

    }

    public void updateUser(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NAME", user.getName());
        values.put("DESCRIPTION", user.getDescription());
        values.put("ID", user.getId());
        values.put("FOLLOWED", user.isFollowed());

        db.update("USERS", values ,"ID = ?", new String[]{String.valueOf(user.getId())});
        db.close();

    }

}