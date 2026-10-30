package com.aiautoshorts.editor.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aiautoshorts.editor.R;
import com.aiautoshorts.editor.models.Project;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private final List<Project> projectList;

    public ProjectAdapter(List<Project> projectList) {
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project project = projectList.get(position);
        holder.tvTitle.setText(project.getTitle());
        int clipCount = project.getGeneratedClips() != null ? project.getGeneratedClips().size() : 0;
        holder.tvSubtitle.setText(clipCount + " Shorts Generated");
    }

    @Override
    public int getItemCount() {
        return projectList != null ? projectList.size() : 0;
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvSubtitle;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_project_title);
            tvSubtitle = itemView.findViewById(R.id.tv_project_subtitle);
        }
    }
}
