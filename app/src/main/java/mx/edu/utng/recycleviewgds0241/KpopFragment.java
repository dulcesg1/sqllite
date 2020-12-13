package mx.edu.utng.recycleviewgds0241;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class KpopFragment extends Fragment {
    //Declaracione
    RecyclerView recyclerView;
    List<Kpop> kpopList;
    MyKpopRecyclerViewAdapter adapterKpop;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public KpopFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static KpopFragment newInstance(int columnCount) {
        KpopFragment fragment = new KpopFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kpop_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            try {
                MyOpenHelper dbHelper = new MyOpenHelper(getContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String consultaSQL = "select * from kpop";

                Cursor resultado = db.rawQuery(consultaSQL, null);
                kpopList = new ArrayList<>();  //Estructura de datos

                while (resultado.moveToNext()) {   //Recorrer el cursor
                    String nombre_grupo = resultado.getString(0);  //Primera columna
                    String photo = resultado.getString(1);
                    float valoracion = resultado.getFloat(2);
                    String nombre_disco = resultado.getString(3);
                    String nombre_cancion = resultado.getString(4);
                    Kpop k = new Kpop(nombre_grupo, photo, valoracion, nombre_disco,nombre_cancion);
                    kpopList.add(k);
                }
            }catch (Exception e){
                Toast.makeText(getContext(),"Error: "+e.getMessage(),Toast.LENGTH_LONG).show();

            }

 //         recyclerView.setAdapter(new MyKpopRecyclerViewAdapter(DummyContent.ITEMS));
            //Agregar elementos a la lista

  /*          kpopList= new ArrayList<>();
            kpopList.add(new Kpop());
            kpopList.add(new Kpop());
            kpopList.add(new Kpop());
*/
            adapterKpop = new MyKpopRecyclerViewAdapter(getActivity(), kpopList);
            recyclerView.setAdapter(adapterKpop);
        }
        return view;
    }
}