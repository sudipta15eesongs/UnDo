package in.bichuti.com.undo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class GameTheme extends AppCompatActivity {

    RadioButton B1,B2,B3;
    Button btm;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
       btm=(Button)findViewById(R.id.ThemeBack) ;
        B1= (RadioButton) findViewById(R.id.RB1);
        B2= (RadioButton) findViewById(R.id.RB2);
        B3= (RadioButton) findViewById(R.id.RB3);
        context=this.getBaseContext();
         B1.setOnCheckedChangeListener(new rbtouch1());
        B2.setOnClickListener(new rbtouch2());
        B3.setOnClickListener(new rbtouch3());
        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityChangeIntent = new Intent(GameTheme.this,MainActivity.class);
                startActivity(activityChangeIntent);
               finish();
            }
        });
    }

    public class rbtouch1 implements RadioButton.OnCheckedChangeListener {



        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            System.out.println("Click");
            FileRW.filewrite("Undotheme.txt","one",context);
        }
    }
    public class rbtouch2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            System.out.println("view");
            FileRW.filewrite("Undotheme.txt","two",context);
        }
    }
    public class rbtouch3 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            FileRW.filewrite("Undotheme.txt","three",context);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed(){

        Intent activityChangeIntent = new Intent(GameTheme.this,MainActivity.class);
        startActivity(activityChangeIntent);
        finish();
    }
}
