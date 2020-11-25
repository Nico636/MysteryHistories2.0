package com.damani.mysteryhistories20.ui.home;

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

import com.damani.mysteryhistories20.Adaptadores.ListAdapterMisterio;
import com.damani.mysteryhistories20.Adaptadores.ListAdapterMuseo;
import com.damani.mysteryhistories20.Adaptadores.ListAdapterTurismo;
import com.damani.mysteryhistories20.R;
import com.damani.mysteryhistories20.Tipos.ListElementMisterio;
import com.damani.mysteryhistories20.Tipos.ListElementMuseo;
import com.damani.mysteryhistories20.Tipos.ListElementTurismo;
import com.damani.mysteryhistories20.iComunica;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ListAdapterMuseo.OnNoteMuseoListener, ListAdapterTurismo.OnNoteTurismoListener, ListAdapterMisterio.OnNoteListener{

    Activity activity;
    iComunica iComunicaFragments;

    RecyclerView recyclerView;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public List<ListElementMuseo> listaMuseos;
    public List<ListElementTurismo> listaTurismo;
    public List<ListElementMisterio> listaMisterio;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
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
                                if(lista.size()<3) {
                                    lista.add(new ListElementMuseo(document.getData().get("nombre").toString(),document.getData().get("direccion").toString(),document.getData().get("valor").toString(),document.getData().get("img_icon").toString(),document.getData().get("historia").toString(),document.getData().get("horario").toString(),document.getData().get("img1").toString(),document.getData().get("img2").toString(),document.getData().get("img3").toString(),document.getData().get("img4").toString(),document.getData().get("telefono").toString(),document.getData().get("ubicacion").toString()));
                                }
                            }
                            if (lista.size() != 0){

                                MostrarMuseos(lista);
                            }

                        }
                    }
                });

        db.collection("turismo")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<ListElementTurismo> lista;
                        lista = new ArrayList<ListElementTurismo>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(lista.size()<3) {
                                    lista.add(new ListElementTurismo(document.getData().get("nombre").toString(),document.getData().get("direccion").toString(),document.getData().get("valor").toString(),document.getData().get("img_icon").toString(),document.getData().get("historia").toString(),document.getData().get("img1").toString(),document.getData().get("img2").toString(),document.getData().get("img3").toString(),document.getData().get("img4").toString(),document.getData().get("ubicacion").toString()));
                                }
                            }
                            if (lista.size() != 0){

                                MostrarTurismo(lista);
                            }

                        }
                    }
                });
        db.collection("misterios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<ListElementMisterio> lista;
                        lista = new ArrayList<ListElementMisterio>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(lista.size()<3) {
                                    lista.add(new ListElementMisterio(document.getData().get("titulo").toString(), document.getData().get("img").toString(), document.getData().get("historia").toString(), document.getData().get("img").toString(), document.getData().get("ubicacion").toString(), document.getData().get("ciudad").toString()));
                                }
                            }
                            if (lista.size() != 0 ){

                                MostrarMisterios(lista);
                            }

                        }
                    }
                });
    }
    public void MostrarTurismo(List<ListElementTurismo> lista){
        ListAdapterTurismo listAdapter = new ListAdapterTurismo(lista, getContext(),this);
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerTurismo);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.setAdapter(listAdapter);
        listaTurismo = lista;
    }
    public void MostrarMuseos(List<ListElementMuseo> lista){
        ListAdapterMuseo listAdapter = new ListAdapterMuseo(lista, getContext(), this);
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerMuseos);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.setAdapter(listAdapter);
        listaMuseos = lista;
    }
    public void MostrarMisterios(List<ListElementMisterio> lista){

        ListAdapterMisterio listAdapter = new ListAdapterMisterio(lista, getContext(),this);
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerMisterios);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.setAdapter(listAdapter);
        listaMisterio = lista;
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
    @Override
    public void onNoteClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("icono",listaMisterio.get(position).getIcono());
        bundle.putString("nombre",listaMisterio.get(position).getTitulo());
        bundle.putString("ciudad",listaMisterio.get(position).getCiudad());
        bundle.putString("historia",listaMisterio.get(position).getHistoria());
        bundle.putString("ubicacion",listaMisterio.get(position).getUbicacion());
        getParentFragmentManager().setFragmentResult("key",bundle);


        Navigation.findNavController(getView()).navigate(R.id.nav_misterios_page);
    }

    @Override
    public void onNoteTurismoClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("icono",listaTurismo.get(position).getIcono());
        bundle.putString("nombre",listaTurismo.get(position).getNombre());
        bundle.putString("historia",listaTurismo.get(position).getHistoria());
        bundle.putString("valor",listaTurismo.get(position).getValor());
        bundle.putString("ubicacion",listaTurismo.get(position).getUbicacion());
        bundle.putString("direccion",listaTurismo.get(position).getDireccion());
        bundle.putString("img1",listaTurismo.get(position).getImg1());
        bundle.putString("img2",listaTurismo.get(position).getImg2());
        bundle.putString("img3",listaTurismo.get(position).getImg3());
        bundle.putString("img4",listaTurismo.get(position).getImg4());
        getParentFragmentManager().setFragmentResult("key",bundle);


        Navigation.findNavController(getView()).navigate(R.id.nav_turismo_page);
    }
}