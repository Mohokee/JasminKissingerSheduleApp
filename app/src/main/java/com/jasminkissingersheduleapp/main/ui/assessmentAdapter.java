package com.jasminkissingersheduleapp.main.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.jasminkissingersheduleapp.R;
import com.jasminkissingersheduleapp.main.entities.Assessment;


import java.util.List;

public class assessmentAdapter extends RecyclerView.Adapter<assessmentAdapter.assessmentViewHolder>{
    class assessmentViewHolder extends RecyclerView.ViewHolder{
        private final TextView assessmentItemView;
        private assessmentViewHolder(View itemView){
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                        final Assessment current=mAssessments.get(position);
                    Intent intent=new Intent(context,assessmentDetailed.class);
                    intent.putExtra("assessId", current.getAssessId());
                    intent.putExtra("assessTitle", current.getAssessTitle());
                    intent.putExtra("assessStart", current.getAssessStart());
                    intent.putExtra("assessEnd",current.getAssessEnd());
                    intent.putExtra("associatedCourseId",current.getAssociatedCourseId());
                    intent.putExtra("status",current.getStatus());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public assessmentAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public assessmentAdapter.assessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_list_item,parent,false);
        return new assessmentAdapter.assessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull assessmentAdapter.assessmentViewHolder holder, int position) {
        if(mAssessments!=null){
            Assessment current=mAssessments.get(position);
            String name=current.getAssessTitle();
            holder.assessmentItemView.setText(name);
        }
        else{
            holder.assessmentItemView.setText("No Assessment Title");
        }

    }

    public void setAssessments(List<Assessment> assessments){
        mAssessments=assessments;
        notifyDataSetChanged();
    }
    public List<Assessment> getAssessments(){
        return mAssessments;
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }

}
