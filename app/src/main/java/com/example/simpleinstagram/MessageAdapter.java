package com.example.simpleinstagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHodler> {

    private Context context;
    private List<Message> messages;

    public MessageAdapter(Context context, List<Message> messages)
    {
        this.context = context;
        this.messages = messages;
    }


    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, viewGroup, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler viewHodler, int i) {

        Message message = messages.get(i);
        viewHodler.bind(message);




    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder {

        private TextView send;
        private TextView text;


        public ViewHodler(@NonNull View itemView) {
            super(itemView);

            send = itemView.findViewById(R.id.tvusername);
            text = itemView.findViewById(R.id.tvuserText);

        }

        public void bind(Message message) {


            send.setText(message.getsend());
            text.setText(message.gettext());


        }
    }

}
