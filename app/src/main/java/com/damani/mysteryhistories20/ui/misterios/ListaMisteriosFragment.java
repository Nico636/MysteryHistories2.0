package com.damani.mysteryhistories20.ui.misterios;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damani.mysteryhistories20.Adaptadores.ListAdapterMisterio;
import com.damani.mysteryhistories20.R;
import com.damani.mysteryhistories20.Tipos.ListElementMisterio;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ListaMisteriosFragment extends Fragment implements ListAdapterMisterio.OnNoteListener {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public List<ListElementMisterio> listaMisterio;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_turismo, container, false);
        init();

        return root;
    }
    public void init(){
        db.collection("misterios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<ListElementMisterio> lista;
                        lista = new ArrayList<ListElementMisterio>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                lista.add(new ListElementMisterio(document.getData().get("titulo").toString(),document.getData().get("img").toString(),document.getData().get("historia").toString(),document.getData().get("img").toString(),document.getData().get("ubicacion").toString(),document.getData().get("ciudad").toString()));
                            }
                            if (lista.size() != 0){

                                Mostrar(lista);
                            }

                        }
                    }
                });
    }
    public void Mostrar(List<ListElementMisterio> lista){

        ListAdapterMisterio listAdapter = new ListAdapterMisterio(lista, getContext(),this);
        RecyclerView recyclerView = getView().findViewById(R.id.listRecycler3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
        listaMisterio = lista;
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
}