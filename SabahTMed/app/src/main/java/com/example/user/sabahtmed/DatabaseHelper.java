package com.example.user.sabahtmed;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by User on 27/2/2017.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.user.sabahtmed/";
    private static String DB_NAME = "sabahtmed.db";
    private static int DATABASE_VERSION;
    private final Context myContext;
    private SQLiteDatabase myDatabase;
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }

    private boolean checkDatabase()
    {
        SQLiteDatabase checkDB=null;
        try{

            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
        }catch(SQLiteException e)
        {

        }

        if(checkDB != null) checkDB.close();
        return checkDB !=null ? true:false;
    }


    private void copyDatabase() throws IOException
    {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;

        while((length = myInput.read(buffer))>0)
        {
            myOutput.write(buffer,0,length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase() throws SQLException
    {
        String myPath= DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);

    }


    public void ExeSQLData(String sql) throws SQLException
    {
        myDatabase.execSQL(sql);
    }

    public Cursor QueryData(String query)throws SQLException{
        return myDatabase.rawQuery(query,null);
    }

    @Override
    public synchronized void close() {
        if (myDatabase != null)
            myDatabase.close();
        super.close();
    }

    public void checkAndCopyDatabase()
    {
        boolean dbExist = checkDatabase();
        if(dbExist)
        {
            Log.d("TAG","Database Already Exist");

        }else
        {
            this.getReadableDatabase();
            try{
                copyDatabase();
            }catch (IOException e)
            {
                Log.d("TAG","Error Copying Database");
            }
        }
    }





}




