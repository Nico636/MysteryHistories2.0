package com.damani.mysteryhistories20.ui.turismo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.damani.mysteryhistories20.Adaptadores.ListAdapterTurismo;
import com.damani.mysteryhistories20.R;
import com.damani.mysteryhistories20.Tipos.ListElementTurismo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListaTurismoFragment extends Fragment  implements ListAdapterTurismo.OnNoteTurismoListener {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public List<ListElementTurismo> listaTurismo;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_turismo, container, false);

        init();

        return root;
    }
    public void init(){
        db.collection("turismo")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<ListElementTurismo> lista;
                        lista = new ArrayList<ListElementTurismo>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                lista.add(new ListElementTurismo(document.getData().get("nombre").toString(),document.getData().get("direccion").toString(),document.getData().get("valor").toString(),document.getData().get("img_icon").toString(),document.getData().get("historia").toString(),document.getData().get("img1").toString(),document.getData().get("img2").toString(),document.getData().get("img3").toString(),document.getData().get("img4").toString(),document.getData().get("ubicacion").toString()));
                            }
                            if (lista.size() != 0){

                                Mostrar(lista);
                            }

                        }
                    }
                });
    }
    public void Mostrar(List<ListElementTurismo> lista){

        ListAdapterTurismo listAdapter = new ListAdapterTurismo(lista, getContext(),this);
        RecyclerView recyclerView = getView().findViewById(R.id.listRecycler3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
        listaTurismo = lista;
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