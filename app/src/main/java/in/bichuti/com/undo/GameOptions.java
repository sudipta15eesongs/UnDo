package in.bichuti.com.undo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;
public class GameOptions extends AppCompatActivity {
    String lstatus;
    ImageView v1,v2,v3;
    Drawable L1,L2;
    Button  B1,B2,B3;
    List<String> readtheme=new ArrayList<String>();
    RelativeLayout RL_O;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameoptions);
       RL_O=(RelativeLayout)findViewById(R.id.GO_RL) ;
        B1=(Button)findViewById(R.id.button);
        B2=(Button)findViewById(R.id.button2);
        B3=(Button)findViewById(R.id.button3);
        v1=(ImageView)findViewById(R.id.IM1);
        v2=(ImageView)findViewById(R.id.IM2);
        v3=(ImageView)findViewById(R.id.IM3);
         L1=ContextCompat.getDrawable(this,R.drawable.complete);
        L2=ContextCompat.getDrawable(this,R.drawable.complete);
        levelstatus();
        // levelstatus();
        if (lstatus.equals("1")){
            v1.setImageDrawable(L1);
        }else if (lstatus.equals("12")){
            v1.setImageDrawable(L1);
            v2.setImageDrawable(L1);
        }else if (lstatus.equals("123")){
            v1.setImageDrawable(L1);
            v2.setImageDrawable(L1);
            v3.setImageDrawable(L1);

        }else {
            v1.setImageDrawable(null);
            v2.setImageDrawable(null);
           v3.setImageDrawable(null);
        }
     B1.setOnClickListener(new touchoption1());
        B2.setOnClickListener(new touchoption2());
        B3.setOnClickListener(new touchoption3());

        //Theme selection
        readtheme=FileRW.fileread("Undotheme.txt",this.getBaseContext());
        String themevalue=readtheme.get(0);

        {
            if (themevalue.equals("one")) {
                RL_O.setBackground(ContextCompat.getDrawable(this, R.drawable.dp1));

            }
            if (themevalue.equals("two")) {
                //System.out.println("cool");
                RL_O.setBackground(ContextCompat.getDrawable(this, R.drawable.flback));

            }
            if (themevalue.equals("three")) {
                RL_O.setBackground(ContextCompat.getDrawable(this, R.drawable.game2));

            }
        }

    }
    public void levelstatus(){
        List<String> readtheme=new ArrayList<String>();
        readtheme=FileRW.fileread("UnDoLevel.txt",this.getBaseContext());
        lstatus=readtheme.get(0);
    };


    public class touchoption1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent activityChangeIntent = new Intent(GameOptions.this, GameActivity.class);
            activityChangeIntent.putExtra("Gametype","Normal");
            activityChangeIntent.putExtra("comwincount",0);
            activityChangeIntent.putExtra("uwincount",0);
            activityChangeIntent.putExtra("globalcount",0);
            activityChangeIntent.putExtra("golballevel",1);
            startActivity(activityChangeIntent);
        }
    }
    public class touchoption2 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (lstatus.equals("1")||lstatus.equals("12")||lstatus.equals("123")){
            Intent activityChangeIntent = new Intent(GameOptions.this, GameActivity.class);
            activityChangeIntent.putExtra("Gametype","Time");
            activityChangeIntent.putExtra("comwincount",0);
            activityChangeIntent.putExtra("uwincount",0);
            activityChangeIntent.putExtra("globalcount",0);

            startActivity(activityChangeIntent);}
            else{
                Toast.makeText(getApplicationContext(), "Previous level is Incomplete",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    public class touchoption3 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if((lstatus.equals("12")||lstatus.equals("123"))){
            Intent activityChangeIntent = new Intent(GameOptions.this, GameActivity.class);
            activityChangeIntent.putExtra("Gametype","Final");
            activityChangeIntent.putExtra("comwincount",0);
            activityChangeIntent.putExtra("uwincount",0);
            activityChangeIntent.putExtra("globalcount",0);

            startActivity(activityChangeIntent);}
            else{

                Toast.makeText(getApplicationContext(), "Previous level is Incomplete",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
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
}
