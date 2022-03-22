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
import com.jasminkissingersheduleapp.main.entities.Course;

import java.util.List;

public class classAdapter extends RecyclerView.Adapter<classAdapter.classViewHolder>{
    class classViewHolder extends RecyclerView.ViewHolder{
        private final TextView classItemView;
        private classViewHolder(View itemView){
            super(itemView);
            classItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Course current=mCourses.get(position);
                    Intent intent=new Intent(context,classDetailed.class);
                    intent.putExtra("courseId", current.getCourseId());
                    intent.putExtra("courseTitle", current.getCourseTitle());
                    intent.putExtra("courseStart", current.getCourseStart());
                    intent.putExtra("courseEnd",current.getCourseEnd());
                    intent.putExtra("courseStatus",current.getCourseStatus());
                    intent.putExtra("instructorName",current.getInstructorName());
                    intent.putExtra("instructorPhone",current.getInstructorPhone());
                    intent.putExtra("instructorEmail",current.getInstructorEmail());
                    intent.putExtra("courseNote",current.getCourseNote());
                    intent.putExtra("termId",current.getAssociatedTermId());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public classAdapter(Context context){
        mInflater=LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public classAdapter.classViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_list_item,parent,false);
        return new classViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull classAdapter.classViewHolder holder, int position) {
        if(mCourses!=null){
            Course current=mCourses.get(position);
            String name=current.getCourseTitle();
            holder.classItemView.setText(name);
        }
        else{
            holder.classItemView.setText("No Class Title");
        }

    }

    public void setCourses(List<Course> classes){
        mCourses=classes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

}
