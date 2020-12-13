package mx.edu.utng.recycleviewgds0241;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    private Button btnRegresar;
    MyOpenHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        String nombregrupo;
        nombregrupo = (String) getIntent().getExtras().getSerializable("nombregrupo");
        final Button btnRegresar=(Button)findViewById(R.id.btnRegresar);
        final Button btnActualizar=(Button)findViewById(R.id.btnActualizar);

        final EditText etNombre=findViewById(R.id.etNombre);
        final EditText etPhoto=findViewById(R.id.etPhoto);
        final EditText etDisco=findViewById(R.id.etDisco);
        final RatingBar rbValoracion=findViewById(R.id.ratingBar);
        final EditText etCancion=findViewById(R.id.etCancion);

        dbHelper=new MyOpenHelper(EditActivity.this);

        try {

            String [] resultado;
            resultado = dbHelper.buscar_kpop(nombregrupo);

             //Estructura de datos

                String nombre_grupo = resultado[0];  //Primera columna
                String photo =resultado[1];
                float valoracion = Float.parseFloat(resultado[2]);
                String nombre_disco = resultado[3];
                String nombre_cancion = resultado[4];
            Toast.makeText(this,resultado[5],Toast.LENGTH_LONG).show();
                etNombre.setText(nombre_grupo);
                etPhoto.setText(photo);
                rbValoracion.setRating(valoracion);
                etDisco.setText(nombre_disco);
                etCancion.setText(nombre_cancion);

                Kpop k = new Kpop(nombre_grupo, photo, valoracion, nombre_disco,nombre_cancion);
        }catch (Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_LONG).show();

        }
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=etNombre.getText().toString();
                String photo=etPhoto.getText().toString();
                String disco=etDisco.getText().toString();
                String cancion=etCancion.getText().toString();

                float valoracion=rbValoracion.getRating();
                Toast.makeText(EditActivity.this, nombre+" "+photo+" "+disco+" "+valoracion+" "+cancion,
                        Toast.LENGTH_LONG).show();
                try {

                    Boolean checkupdatedata = dbHelper.updatekpopdata(nombre,photo,valoracion,disco,cancion);

                    etNombre.setText("");
                    etPhoto.setText("");
                    rbValoracion.setRating(0.0f);
                    etDisco.setText("");
                    etCancion.setText("");
                    if(checkupdatedata==true){
                        Toast.makeText(EditActivity.this, "Se han actualizado los datos",
                                Toast.LENGTH_LONG).show();}
                }catch (Exception ex){
                    Toast.makeText(EditActivity.this, "Error:"+ex.getMessage(),
                            Toast.LENGTH_LONG).show();
                }


            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intentMain);  //Inicia MainActivity
                finish(); //Finaliza NuevoActivity
            }
        });


    }
}