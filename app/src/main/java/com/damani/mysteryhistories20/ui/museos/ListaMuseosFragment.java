package com.damani.mysteryhistories20.ui.museos;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.damani.mysteryhistories20.Adaptadores.ListAdapterMuseo;
import com.damani.mysteryhistories20.R;
import com.damani.mysteryhistories20.Tipos.ListElementMuseo;
import com.damani.mysteryhistories20.iComunica;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListaMuseosFragment extends Fragment  implements ListAdapterMuseo.OnNoteMuseoListener {

    Activity activity;
    iComunica iComunicaFragments;

    RecyclerView recyclerView;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public List<ListElementMuseo> listaMuseos;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_museo, container, false);
        recyclerView = root.findViewById(R.id.recyclerView4);

        init();

        return root;
    }
    public void init(){

        db.collection("museos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<ListElementMuseo> lista;
                        lista = new ArrayList<ListElementMuseo>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                lista.add(new ListElementMuseo(document.getData().get("nombre").toString(),document.getData().get("direccion").toString(),document.getData().get("valor").toString(),document.getData().get("img_icon").toString(),document.getData().get("historia").toString(),document.getData().get("horario").toString(),document.getData().get("img1").toString(),document.getData().get("img2").toString(),document.getData().get("img3").toString(),document.getData().get("img4").toString(),document.getData().get("telefono").toString(),document.getData().get("ubicacion").toString()));
                            }
                            if (lista.size() != 0){

                                Mostrar(lista);
                            }

                        }
                    }
                });


    }
    public void Mostrar(List<ListElementMuseo> lista){
        ListAdapterMuseo listAdapter = new ListAdapterMuseo(lista, getContext(), this);
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
        listaMuseos = lista;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onNoteMuseoClick(int position) {

        Bundle bundle = new Bundle();
        bundle.putString("icono",listaMuseos.get(position).getIcono());
        bundle.putString("nombre",listaMuseos.get(position).getNombre());
        bundle.putString("historia",listaMuseos.get(position).getHistoria());
        bundle.putString("valor",listaMuseos.get(position).getValor());
        bundle.putString("telefono",listaMuseos.get(position).getTelefono());
        bundle.putString("ubicacion",listaMuseos.get(position).getUbicacion());
        bundle.putString("horario",listaMuseos.get(position).getHorario());
        bundle.putString("direccion",listaMuseos.get(position).getDireccion());
        bundle.putString("img1",listaMuseos.get(position).getImg1());
        bundle.putString("img2",listaMuseos.get(position).getImg2());
        bundle.putString("img3",listaMuseos.get(position).getImg3());
        bundle.putString("img4",listaMuseos.get(position).getImg4());
        getParentFragmentManager().setFragmentResult("key",bundle);


        Navigation.findNavController(getView()).navigate(R.id.nav_museo_page);




    }
/*
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.activity = (Activity) context;
            iComunicaFragments = (iComunica)this.activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }*/
}