package com.jasminkissingersheduleapp.main.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jasminkissingersheduleapp.R;
import com.jasminkissingersheduleapp.main.entities.Term;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class termAdapter extends RecyclerView.Adapter<termAdapter.termViewHolder> implements Filterable {
    class termViewHolder extends RecyclerView.ViewHolder{
        private final TextView termItemView;

        private termViewHolder(View itemView){
            super(itemView);
            termItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Term current=mTerms.get(position);
                    Intent intent=new Intent(context,termDetailed.class);
                    intent.putExtra("id", current.getTermId());
                    intent.putExtra("termTitle", current.getTermTitle());
                    intent.putExtra("termStart", current.getTermStart());
                    intent.putExtra("termEnd",current.getTermEnd());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Term> mTerms;

    //New 3/21/2022- adding search functionality to term list page
    private List<Term> mTermFiltered;


    private final Context context;
    private final LayoutInflater mInflater;

    public termAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public termAdapter.termViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_list_item,parent,false);
        return new termViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull termAdapter.termViewHolder holder, int position) {
        if(mTerms!=null){
            Term current=mTerms.get(position);
            String name=current.getTermTitle();
            holder.termItemView.setText(name);
        }
        else{
            holder.termItemView.setText("No Term Title");
        }

    }

    public void setTerms(List<Term> terms){
        mTerms=terms;

        //this array is used to hold the filtered terms
        mTermFiltered=new ArrayList<>(mTerms);

        notifyDataSetChanged();
    }
    public List<Term> getTerms(){
        return mTerms;
    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }


    //Adds a filter for the search view
    public Filter getFilter() {
        return termFilter;
    }

    private Filter termFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Term> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(mTermFiltered);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(Term t: mTermFiltered) {
                    if(t.getTermTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(t);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mTerms.clear();
            mTerms.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    //end March 2022 filter amendments
}
