package sg.edu.np.practical2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Intent receive = getIntent();

    //User user = new User("pp", receive.getStringExtra("Des"), 1, false);
    User user;
    private TextView txtName;
    private TextView cl;

    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("debug", "create");
        this.txtName = findViewById(R.id.txtName);
        this.cl = findViewById(R.id.cl);
        Button button = (Button) findViewById(R.id.btnFollow);
        Intent receive = getIntent();
        dbHandler = new DBHandler(MainActivity.this);
//
        //User user = new User(receive.getStringExtra("Name"), receive.getStringExtra("Des"), 1, false);
        int id = receive.getIntExtra("id",0);
        user = ListActivity.userList.get(id);

        this.txtName.setText(user.getName() );
        this.cl.setText(user.getDescription());

        if(user.followed == false)
        {
            button.setText("Follow");
        }
        else
        {
            button.setText("Unfollow");
        }


        button.setOnClickListener(v -> {
            //int status = (Integer) v.getTag();
            if (user.followed == false){
                Context context = getApplicationContext();
                CharSequence text = "followed";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(context, text, duration ).show();

                button.setText("Unfollow");
                Log.d("debug", "Unfollowed");



                user.setFollowed(true);
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "unfollowed";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(context, text, duration ).show();
                button.setText("Follow");
                Log.d("debug", "Followed");

                user.setFollowed(false);
            }
            dbHandler.updateUser(user);
        });
//        ArrayList<User> data = new ArrayList<User>();
//        for (int i=0 ; i<20; i++){
//            User u = new User();
//            Random rand = new Random();
//            String txt = String.valueOf(rand.nextInt());
//            u.setName("Name" + txt);
//            u.setDescription(txt);
//            data.add(u);
//
//        }
//        RecyclerView rv = findViewById(R.id.scroll);







    }

//    private void setUser(User user){
//        this.user = user;
//        this.updateUserInfo();
//
//    }
//
//    private void updateUserInfo() {
//        //Intent receive = getIntent();
//        this.textBiew.setText(user.getName() );
//        this.cl.setText(user.getDescription());
//    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug", "Start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "Stop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "Pause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug", "Destroy");
    }


}
