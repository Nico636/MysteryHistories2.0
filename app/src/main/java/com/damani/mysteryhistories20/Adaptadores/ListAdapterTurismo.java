package com.damani.mysteryhistories20.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.damani.mysteryhistories20.R;
import com.damani.mysteryhistories20.Tipos.ListElementMuseo;
import com.damani.mysteryhistories20.Tipos.ListElementTurismo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapterTurismo extends RecyclerView.Adapter<ListAdapterTurismo.ViewHolder>  {
    private List<ListElementTurismo> mData;
    private LayoutInflater mInglater;
    private Context context;
    private ListAdapterTurismo.OnNoteListener mOnNoteListener;

    public ListAdapterTurismo(List<ListElementTurismo> itemList, Context context, ListAdapterTurismo.OnNoteListener onNoteListener){
        this.mInglater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.mOnNoteListener = onNoteListener;

    }
    @Override
    public int getItemCount(){
        return mData.size();
    }
    @Override
    public ListAdapterTurismo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInglater.inflate(R.layout.list_element, null);
        return new ListAdapterTurismo.ViewHolder(view, mOnNoteListener);
    }
    @Override
    public void onBindViewHolder(final ListAdapterTurismo.ViewHolder holder , final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListElementTurismo> items){
        mData = items;
    }
    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iconImage;
        TextView name, city, status;
        OnNoteListener onNoteListener;
        ViewHolder(View itemView, OnNoteListener onNoteListener){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.nameTextView);
            city = itemView.findViewById(R.id.cityTextView);
            status = itemView.findViewById(R.id.statusTextView);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        void bindData(final ListElementTurismo item){
            Picasso.with(context).load(item.getIcono()).into(iconImage);
            name.setText(item.getNombre());
            city.setText(item.getDireccion());
            status.setText(item.getValor());
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void onNoteClick(int position);

    }
}
