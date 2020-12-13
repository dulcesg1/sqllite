package mx.edu.utng.recycleviewgds0241;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String REST_TABLE_CREATE="CREATE TABLE kpop (nombre_grupo TEXT PRIMARY KEY ,"+"photo TEXT, valoracion REAL, nombre_disco TEXT,nombre_cancion TEXT)";
    private static final String DB_NAME="kpopdb.sqlite";
    private static final int DB_VERSION=1;


    public MyOpenHelper( Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(REST_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists kpop");
    }
    public Boolean insertkpopdata(String nombre_grupo, String photo, float valoracion,String nombre_disco,String nombre_cancion)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre_grupo", nombre_grupo);
        contentValues.put("photo", photo);
        contentValues.put("valoracion", valoracion);
        contentValues.put("nombre_disco", nombre_disco);
        contentValues.put("nombre_cancion", nombre_cancion);

        long result=DB.insert("kpop", null, contentValues);
            DB.close();
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public Boolean updatekpopdata(String nombre_grupo, String photo, float valoracion,String nombre_disco,String nombre_cancion) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("photo", photo);
        contentValues.put("valoracion", valoracion);
        contentValues.put("nombre_disco", nombre_disco);
        contentValues.put("nombre_cancion", nombre_cancion);
        Cursor cursor = DB.rawQuery("Select * from kpop where nombre_grupo = ?", new String[]{nombre_grupo});

        if (cursor.getCount() > 0) {
            long result = DB.update("kpop", contentValues, "nombre_grupo=?", new String[]{nombre_grupo});
            DB.close();
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public Boolean deletekpopdata (String nombre_grupo)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from kpop where nombre_grupo = ?", new String[]{nombre_grupo});
        if (cursor.getCount() > 0) {
            long result = DB.delete("kpop", "nombre_grupo=?", new String[]{nombre_grupo});
            DB.close();
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }
    public String[] buscar_kpop(String nombre_grupo){
        String [] datos= new String[6];
        SQLiteDatabase db=this.getWritableDatabase();
        String q="SELECT * FROM kpop WHERE nombre_grupo='"+nombre_grupo+"'";
        Cursor registros=db.rawQuery(q,null);
        if (registros.moveToFirst()){
        for (int i=0;i<5;i++){
            datos[i]=registros.getString(i);
        }
        datos[5]="Se encontraron los datos";
        }else{
            datos[5]="No se encotro el registro";
        }
        return datos;

    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from kpop", null);
        return cursor;

    }
}
