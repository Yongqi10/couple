package com.example.simpleinstagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simpleinstagram.LoginActivity;
import com.example.simpleinstagram.Message;
import com.example.simpleinstagram.MessageAdapter;
import com.example.simpleinstagram.R;
import com.example.simpleinstagram.postsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private final String TAG = "MessageFragment";

    private RecyclerView rvMessage;
    private Button btnSend;
    private MessageAdapter MAdapter;
    private EditText etMessage;
    private List<Message> allmessage;
    public MessageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMessage = view.findViewById(R.id.rvMessage);
        allmessage = new ArrayList<>();

        MAdapter = new MessageAdapter(getContext(),allmessage);

        rvMessage.setAdapter(MAdapter);
        rvMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        btnSend = view.findViewById(R.id.btnSend);
        etMessage = view.findViewById(R.id.textMessage);

        getMessage();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etMessage.getText().toString();
                if(text.isEmpty())
                {
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                //gg is user2
                sendthemessage(currentUser,"gg",text);


            }
        });

    }

    private void sendthemessage(ParseUser currentUser,String receive, String text) {

        Message message = new Message();
        //user 1
        message.setsend(currentUser.getUsername());
        //text
        message.settext(text);
        //user 2
        message.setreceive(receive);
        message.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null) {
                    Log.e(TAG,"Error while sending",e);
                    return;
                }
                Log.i(TAG,"message send was successful");
                etMessage.setText("");
            }
        });


    }

    private void getMessage() {

        ParseQuery<Message> query = ParseQuery.getQuery(Message.class);

        query.findInBackground(new FindCallback<Message>() {
            @Override
            public void done(List<Message> objects, ParseException e) {
                if(e!= null)
                {
                    Log.e(TAG,"Issue with getting message",e);
                    return;
                }
                for(Message message: objects)
                {
                    Log.i(TAG,"Message: "+ message.gettext() + "username"+message.getsend());
                }

                ParseUser currentUser = ParseUser.getCurrentUser();
                //only get the data between user 1 and user 2
                // gg is user 2
                for(Message message: objects)
                {
                    if((currentUser.getUsername().equals(message.getsend()) && message.getreceive().equals("gg"))||
                            (message.getsend().equals("gg") && currentUser.getUsername().equals(message.getreceive())))
                    {
                        allmessage.add(message);
                    }
                }

              //  allmessage.addAll(objects);
                MAdapter.notifyDataSetChanged();
            }
        });



    }


}