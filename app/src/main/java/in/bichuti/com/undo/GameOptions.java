package in.bichuti.com.undo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.widget.Toast;
public class GameOptions extends AppCompatActivity {
    String lstatus;
    ImageView v1,v2,v3;
    TextView Wt;
    Drawable L1,L2;
    Button  B1,B2,B3,EM;
    List<String> readtheme=new ArrayList<String>();
    List<String> Codelist=new ArrayList<String>();
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
        EM=(Button) findViewById(R.id.Email);
        Wt=(TextView) findViewById(R.id.Winmsg);
        EM.setVisibility(View.GONE);
         L1=ContextCompat.getDrawable(this,R.drawable.complete);
        L2=ContextCompat.getDrawable(this,R.drawable.complete);
        levelstatus();

        //codelist
        Codelist.add("A");
        Codelist.add("B");
        Codelist.add("C");
        Codelist.add("D");
        Codelist.add("E");
        Codelist.add("F");
        Codelist.add("G");
        Codelist.add("H");
        Codelist.add("I");
        Codelist.add("J");
        Codelist.add("K");
        Codelist.add("L");
        Codelist.add("M");
        Codelist.add("N");
        Codelist.add("O");
        Codelist.add("P");
        Codelist.add("Q");
        Codelist.add("R");
        Codelist.add("S");
        Codelist.add("T");
        Codelist.add("U");
        Codelist.add("V");
        Codelist.add("W");
        Codelist.add("X");
        Codelist.add("Y");
        Codelist.add("Z");


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
            EM.setVisibility(View.VISIBLE);
            Wt.setText("Congrates! Claim your prize by clicking Email button");
            Wt.setTextSize(18);
            Wt.setTextColor(Color.parseColor("#FDFF00"));
            Wt.setBackgroundColor(Color.BLACK);

        }else {
            v1.setImageDrawable(null);
            v2.setImageDrawable(null);
           v3.setImageDrawable(null);
        }
     B1.setOnClickListener(new touchoption1());
        B2.setOnClickListener(new touchoption2());
        B3.setOnClickListener(new touchoption3());
       EM.setOnClickListener(new touchoption4());
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
            finish();
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

            startActivity(activityChangeIntent);
                finish();
            }
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
            startActivity(activityChangeIntent);
            finish();
            }
            else{

                Toast.makeText(getApplicationContext(), "Previous level is Incomplete",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    public class touchoption4 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
         int codenum=numgenerator(16,0);
         String alp1=getalpha(codenum)   ;
            int num2=numgenerator(9000,7000);
            int num3=17;
            int codenum2=numgenerator(12,8);
            String alp2=getalpha(codenum2);
            int num4=1201;
            int num5=numgenerator(59,40);
            int num6=numgenerator(5,1);
String Code=alp1.concat(String.valueOf(num3)).concat(String.valueOf(num2)).concat(alp2).concat(String.valueOf(num4)).concat(String.valueOf(num5)).concat(String.valueOf(num6));
            String msgtext="Congrates!! you have completed all the levels" +"\n"+
                    "Claim_Code:"+Code +"\n"+"(Do not modify the generated code)"+"\n"+"Name:"+"\n"+"Phone";
            Context ct=getBaseContext();
            File f1=ct.getFileStreamPath("Code.txt");
            if (!f1.exists()){
                try {
                    FileOutputStream fileout1 = getBaseContext().openFileOutput("Code.txt", MODE_PRIVATE);
                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout1);
                    outputWriter.write(Code);
                    outputWriter.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Claim@newgamesindia");
            intent.putExtra(Intent.EXTRA_TEXT, msgtext);
            intent.setData(Uri.parse("mailto:newgamesindia@gmail.com")); // or just "mailto:" for blank
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
            startActivity(intent);
            }else{

                Toast.makeText(getApplicationContext(), "Claim has already been done. Try with new installation",
                        Toast.LENGTH_LONG).show();

            }
        }
    }
    public int numgenerator(int max,int min){
        Random r = new Random();
        int i1 = r.nextInt(max - min) + min;
        return i1;
    };
    public String getalpha(int n){
        String al1=Codelist.get(n);
        String al2=Codelist.get(n+6);

        return al2.concat(al1) ;
    };



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
