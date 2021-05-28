package sg.edu.np.practical2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView name;
    TextView description;
    public UserViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        description = itemView.findViewById(R.id.des);
        img = itemView.findViewById(R.id.img_profile);



    }

}
