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

    User user;
    private TextView txtName;
    private TextView cl;

    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btnFollow);
        this.txtName = findViewById(R.id.txtName);
        this.cl = findViewById(R.id.cl);
        Intent receive = getIntent();
        dbHandler = new DBHandler(MainActivity.this);
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

    }



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
