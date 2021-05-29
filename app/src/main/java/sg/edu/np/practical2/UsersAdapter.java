package sg.edu.np.practical2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {
    Context context;
    ArrayList<User> userList;
    public UsersAdapter(Context c, ArrayList<User> d){
        context = c;
        userList = d;

    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view, parent, false);
        View item = null;
        if(viewType == 0)
        {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view7, parent, false);
        }
        else{
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view, parent, false);
        }
        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User u = userList.get(position);

        holder.name.setText(u.getName());
        holder.description.setText(u.getDescription());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Profile");
                builder.setMessage(u.getName());
                builder.setCancelable(true);
                builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Intent i = new Intent(context, MainActivity.class);
                        i.putExtra("id", position);
                        //i.putExtras(extras);
                        context.startActivity(i);



                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String name = userList.get(position).getName();
        int ld = Integer.parseInt(name.substring(name.length() - 1));
        if (ld == 7)
            return 0;
        return 1;
    }
}
