package mx.edu.utng.recycleviewgds0241;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Kpop}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyKpopRecyclerViewAdapter extends RecyclerView.Adapter<MyKpopRecyclerViewAdapter.ViewHolder> {

    private final List<Kpop> mValues;
    private Context contexto;
    MyOpenHelper dbHelper;

    public MyKpopRecyclerViewAdapter(Context ctx, List<Kpop> items) {

        contexto=ctx;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_kpop, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvNombreGrupo.setText(holder.mItem.getNombre_grupo());
        holder.tvDisco.setText(holder.mItem.getNombre_disco());
        holder.rbKpop.setRating(holder.mItem.getValoracion());
        holder.tvCancion.setText(holder.mItem.getNombre_cancion());
        holder.btnEditar.setTag(holder.mItem.getNombre_grupo());
        holder.btnEliminar.setTag(holder.mItem.getNombre_grupo());
        Glide.with(contexto).load(holder.mItem.getUrlPhoto()).centerCrop().placeholder(R.drawable.ic_launcher_background).error(R.drawable.musica).into(holder.ivPhoto);

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intNuevo=new Intent(contexto,EditActivity.class);

                final String parametro= holder.btnEditar.getTag().toString();
                intNuevo.putExtra("nombregrupo", parametro);
                contexto.startActivity(intNuevo);

            }
        });
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nombre= holder.btnEditar.getTag().toString();
                try{

                    Toast.makeText(contexto, nombre,
                            Toast.LENGTH_LONG).show();
                    dbHelper=new MyOpenHelper(contexto);
                    Boolean checkdeletedata = dbHelper.deletekpopdata(nombre);
                    if(checkdeletedata==true){
                        Toast.makeText(contexto, "Se ha eliminado el grupo",
                                Toast.LENGTH_LONG).show();
                        Intent intNuevo=new Intent(contexto,MainActivity.class);
                        contexto.startActivity(intNuevo);

                    }
                }catch (Exception ex){
                    Toast.makeText(contexto, "Error: "+ex.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvNombreGrupo;
        public final TextView tvCancion;
        public final ImageView ivPhoto;
        public final RatingBar rbKpop;
        public final TextView tvDisco;
        public final Button btnEditar;
        public final Button btnEliminar;

        public Kpop mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvNombreGrupo = (TextView) view.findViewById(R.id.tvNombreGrupo);
            tvCancion = (TextView) view.findViewById(R.id.tvCancion);
            ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);
            rbKpop = (RatingBar) view.findViewById(R.id.rbKpop);
            tvDisco = (TextView) view.findViewById(R.id.tvDisco);
            btnEditar = (Button) view.findViewById(R.id.btnEditar);
            btnEliminar = (Button) view.findViewById(R.id.btnEliminar);



        }

        @Override
        public String toString() {
            return super.toString() ;
        }

    }

}