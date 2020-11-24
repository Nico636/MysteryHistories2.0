package com.damani.mysteryhistories20.ui.misteriosPage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.damani.mysteryhistories20.R;
import com.damani.mysteryhistories20.ui.MuseoPage.MapsMuseo2;
import com.damani.mysteryhistories20.ui.MuseoPage.MuseoMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;


public class MisteriosPageFragment extends Fragment {
    /*
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;*/
    private ImageView imagen;
    private TextView txTitulo,txCiudad,txHistoria;
    private Button btn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MisteriosPageFragment() {
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
                txTitulo.setText(result.getString("nombre"));
                txCiudad.setText(result.getString("ciudad"));
                txHistoria.setText(result.getString("historia"));


                String icono = result.getString("icono");
                Picasso.with(getContext()).load(icono).into(imagen);


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
        View v = inflater.inflate(R.layout.fragment_misterios_page, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imagen = view.findViewById(R.id.imagen);
        txCiudad = view.findViewById(R.id.ciudadTxV);
        txHistoria = view.findViewById(R.id.historiaTxV);
        txTitulo = view.findViewById(R.id.nombreTxV);
        btn = view.findViewById(R.id.btnMapa);
    }
    public void AbrirMapa(){

        db.collection("misterios")
                .whereEqualTo("titulo", txTitulo.getText().toString())
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



        Intent act = new Intent(getContext(), MapsMuseo2.class);
        act.putExtra("Lat", Cinco[0]);
        act.putExtra("Lng", Siete);
        act.putExtra("Nombre", txTitulo.getText().toString());
        startActivity(act);
    }
}