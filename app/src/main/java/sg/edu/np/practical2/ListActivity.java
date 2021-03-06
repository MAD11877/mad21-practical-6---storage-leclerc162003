package sg.edu.np.practical2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity{
    public static ArrayList<User> userList = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        DBHandler db = new DBHandler(this);
        userList = db.getUsers();
        if (userList.size() != 20){
            for (int i=0 ; i<20; i++){
                User u = new User();
                Random rand = new Random();
                String txt = String.valueOf(rand.nextInt());
                int randomFollow = rand.nextInt(2);
                u.setId(rand.nextInt());
                u.setName("Name" + txt);
                u.setDescription("Description" + txt);
                u.setId(i+1);
                if (randomFollow == 1){
                    u.setFollowed(true);
                }
                else{
                    u.setFollowed(false);
                }
//            data.add(u);
                db.addUser(u);

            }
            userList = db.getUsers();
        }
        RecyclerView rv = findViewById(R.id.rv);
        UsersAdapter adapter = new UsersAdapter(this, userList);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);


    }


}