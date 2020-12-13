package mx.edu.utng.recycleviewgds0241;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class NuevoActivity extends AppCompatActivity {
    private Button btnAgregar,btnRegresar;
    MyOpenHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_ativity);
        final Button btnAgregar=(Button)findViewById(R.id.btnAgregar);
        final Button btnRegresar=(Button)findViewById(R.id.btnRegresar);

        final EditText etNombre=findViewById(R.id.etNombre);
        final EditText etPhoto=findViewById(R.id.etPhoto);
        final EditText etDisco=findViewById(R.id.etDisco);
        final RatingBar rbValoracion=findViewById(R.id.ratingBar);
        final EditText etCancion=findViewById(R.id.etCancion);

        dbHelper=new MyOpenHelper(NuevoActivity.this);

                btnAgregar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nombre=etNombre.getText().toString();
            String photo=etPhoto.getText().toString();
            String disco=etDisco.getText().toString();
            float valoracion=rbValoracion.getRating();
            String cancion=etCancion.getText().toString();
            Toast.makeText(NuevoActivity.this, nombre+photo+disco,
                    Toast.LENGTH_LONG).show();
            try {

                    Boolean revInrest=dbHelper.insertkpopdata(nombre,photo,valoracion,disco,cancion);

                    etNombre.setText("");
                    etPhoto.setText("");
                    rbValoracion.setRating(0.0f);
                    etDisco.setText("");
                    etCancion.setText((""));
                    if(revInrest==true){
                    Toast.makeText(NuevoActivity.this, "Se ha agregado un nuevo Grupo de Kpop",
                            Toast.LENGTH_LONG).show();}else{
                        Toast.makeText(NuevoActivity.this, "NO Se ha agregado el nuevo grupo",
                                Toast.LENGTH_LONG);
                    }
                }catch (Exception ex){
                    Toast.makeText(NuevoActivity.this, "Error:"+ex.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

        }
    });


        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(NuevoActivity.this, MainActivity.class);
                startActivity(intentMain);  //Inicia MainActivity
                finish(); //Finaliza NuevoActivity
            }
        });

    }
}