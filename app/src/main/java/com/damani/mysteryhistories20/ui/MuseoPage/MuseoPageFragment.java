package com.damani.mysteryhistories20.ui.MuseoPage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.damani.mysteryhistories20.Inicio;
import com.damani.mysteryhistories20.R;
import com.damani.mysteryhistories20.Tipos.ListElementMuseo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.io.Serializable;


public class MuseoPageFragment extends Fragment  {
/*
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;*/
    private ImageView imagen, img1,img2,img3,img4;
    private TextView txNombre,txDireccion,txHorario,txHistoria,txValorEntrada,txTelefono;
    private Button btn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MuseoPageFragment() {
        // Required empty public constructor
    }

   /* // TODO: Rename and change types and number of parameters
    public static MuseoPageFragment newInstance(String param1, String param2) {
        MuseoPageFragment fragment = new MuseoPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                txNombre.setText(result.getString("nombre"));
                txDireccion.setText(result.getString("direccion"));
                txHistoria.setText(result.getString("historia"));
                txHorario.setText(result.getString("horario"));
                txTelefono.setText(result.getString("telefono"));
                txValorEntrada.setText(result.getString("valor"));

                String icono = result.getString("icono");
                Picasso.with(getContext()).load(icono).into(imagen);
                String icono1 = result.getString("img1");
                Picasso.with(getContext()).load(icono1).into(img1);
                String icono2 = result.getString("img2");
                Picasso.with(getContext()).load(icono2).into(img2);
                String icono3 = result.getString("img3");
                Picasso.with(getContext()).load(icono3).into(img3);
                String icono4 = result.getString("img4");
                Picasso.with(getContext()).load(icono4).into(img4);
                img1.setMinimumWidth(1000);
                img1.setMinimumHeight(1000);
                img2.setMinimumWidth(1000);
                img2.setMinimumHeight(1000);
                img3.setMinimumWidth(1000);
                img3.setMinimumHeight(1000);
                img4.setMinimumWidth(1000);
                img4.setMinimumHeight(1000);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AbrirMapa();
                    }
                });
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_museo_page, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imagen = view.findViewById(R.id.imagen);
        img1 = view.findViewById(R.id.imagen_1);
        img2 = view.findViewById(R.id.imagen_2);
        img3 = view.findViewById(R.id.imagen_3);
        img4 = view.findViewById(R.id.imagen_4);
        txDireccion = view.findViewById(R.id.direccionTxV);
        txHistoria = view.findViewById(R.id.historiaTxV);
        txHorario = view.findViewById(R.id.horarioTxV);
        txNombre = view.findViewById(R.id.nombreTxV);
        txValorEntrada = view.findViewById(R.id.valorTxV);
        txTelefono = view.findViewById(R.id.telefonoTxV);
        btn = view.findViewById(R.id.btnMapa);
    }
    public void AbrirMapa(){

        db.collection("museos")
                .whereEqualTo("nombre", txNombre.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String coord = document.getData().get("ubicacion").toString();
                                AbrirMapa2(coord);
                            }

                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
    public void AbrirMapa2(String coord) {


        String Uno = coord.replace('{', ' ');
        String Dos = Uno.replace('}', ' ');
        String Tres = Dos.trim();
        String Cuatro = Tres.substring(20);
        String[] Cinco = Cuatro.split(",", 2);


        String Seis = Cinco[1].substring(11);
        String Siete = Seis.trim();

/*
        Bundle bundle = new Bundle();
        bundle.putString("lat",Cinco[0]);
        bundle.putString("lng",Siete);
        bundle.putString("Nombre",txNombre.getText().toString());
        getParentFragmentManager().setFragmentResult("key2",bundle);


        Navigation.findNavController(getView()).navigate(R.id.nav_maps);
        */
        Intent act = new Intent(getContext(), MapsMuseo2.class);
        act.putExtra("Lat", Cinco[0]);
        act.putExtra("Lng", Siete);
        act.putExtra("Nombre", txNombre.getText().toString());
        startActivity(act);
    }
}