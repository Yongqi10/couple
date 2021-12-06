package com.example.simpleinstagram;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class postsAdapter extends RecyclerView.Adapter<postsAdapter.ViewHolder>{

    private Context context;
    private List<Post> posts;

    public postsAdapter(Context context,List<Post>posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder  extends RecyclerView.ViewHolder {
        private TextView tvusername;
        private ImageView ivimage;
        private TextView tvdescription;
        public ViewHolder(View itemView)
        {
            super(itemView);
            tvusername = itemView.findViewById(R.id.tvUsername);
            tvdescription = itemView.findViewById(R.id.tvDescription);
            ivimage  = itemView.findViewById(R.id.ivimage);
        }

        public void bind(Post post) {
            tvdescription.setText(post.getDescription());
            tvusername.setText(post.getUSer().getUsername());
            ParseFile image = post.getImage();
            if(image !=null)
                Glide.with(context).load(post.getImage().getUrl()).into(ivimage);

        }
    }
}
