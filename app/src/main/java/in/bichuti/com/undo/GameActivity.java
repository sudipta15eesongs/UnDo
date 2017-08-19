package in.bichuti.com.undo;


        import android.content.ClipData;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.Color;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.os.CountDownTimer;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.view.DragEvent;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;


        import java.io.ByteArrayOutputStream;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;
        import java.util.concurrent.TimeUnit;

        import de.hdodenhof.circleimageview.CircleImageView;

public class GameActivity extends AppCompatActivity {
       ImageView iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9,display1,SC1,SC2;
    TextView display2,playemsg,scoremsg;
    Button pause;
    String player1check,player2check,gametype;
    int win,ucount,comcount,gcount;
    boolean whowin;


    ArrayList<Integer> myResources=new ArrayList<Integer>();
    ArrayList<String> finaldes=new ArrayList<String>();
    ArrayList<String> player1win=new ArrayList<String>();
    ArrayList<String> player2win=new ArrayList<String>();
    ArrayList<ImageView> imobj=new ArrayList<ImageView>();
    ArrayList<String> comlogic=new ArrayList<String>();
    ArrayList<String> decesion=new ArrayList<String>();
    Context mContext;
    Drawable d1,d2,d3,d4,d5,dw;
    Timelaps tlps;
    long timetilfinish,timetilgo,maintimecount;
    RelativeLayout rl1;

    CountDownTimer  timr, timr2;
    String playermsg;
    private static final String FORMAT = "%02d:%02d";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
       maintimecount=60000;
        finaldes.add("1,2,4,5");
        finaldes.add("2,1,3,5");
        finaldes.add("3,2,5,6");
        finaldes.add("4,1,5,7");
        finaldes.add("5,1,2,3,4,6,7,8,9");
        finaldes.add("6,3,5,9");
        finaldes.add("7,4,5,8");
        finaldes.add("8,5,7,9");
        finaldes.add("9,5,6,8");

        playermsg="HI";
        //Assigning value
        iv1=(ImageView)findViewById(R.id.v1);
        iv2=(ImageView)findViewById(R.id.v2);
        iv3=(ImageView)findViewById(R.id.v3);
        iv4=(ImageView)findViewById(R.id.v4);
        iv5=(ImageView)findViewById(R.id.v5);
        iv6=(ImageView)findViewById(R.id.v6);
        iv7=(ImageView)findViewById(R.id.v7);
        iv8=(ImageView)findViewById(R.id.v8);
        iv9=(ImageView)findViewById(R.id.v9);
        SC1=(ImageView)findViewById(R.id.Scorered);
        SC2=(ImageView)findViewById(R.id.Scoreblue);
        display1=(ImageView)findViewById(R.id.Dip1);
        display2=(TextView)findViewById(R.id.textView2);
        playemsg=(TextView)findViewById(R.id.textView1);
        scoremsg= (TextView)findViewById(R.id.ScoreText);
        pause=(Button)findViewById(R.id.Pause) ;
        rl1=(RelativeLayout)findViewById(R.id.RL2);
        pause.setOnClickListener(new pauseaction());
        imobj.add(iv1);
        imobj.add(iv2);
        imobj.add(iv3);
        imobj.add(iv4);
        imobj.add(iv5);
        imobj.add(iv6);
        imobj.add(iv7);
        imobj.add(iv8);
        imobj.add(iv9);

        myResources.add(R.id.v1);
        myResources.add(R.id.v2);
        myResources.add(R.id.v3);
        myResources.add(R.id.v4);
        myResources.add(R.id.v5);
        myResources.add(R.id.v6);
        myResources.add(R.id.v7);
        myResources.add(R.id.v8);
        myResources.add(R.id.v9);
        mContext= this.getApplicationContext();
        //d1=ContextCompat.getDrawable(this,R.drawable.ball1);
       // d2=ContextCompat.getDrawable(this,R.drawable.ball2);
        d3=ContextCompat.getDrawable(this,R.drawable.ball3);
        d1=ContextCompat.getDrawable(this,R.drawable.fl1);
        d2=ContextCompat.getDrawable(this,R.drawable.fl2);
        dw=ContextCompat.getDrawable(this,R.drawable.flback);





        //setting ondrag listener
        iv4.setOnDragListener(new dragdrop());
        iv5.setOnDragListener(new dragdrop());
        iv6.setOnDragListener(new dragdrop());
        player1winadd();
        player2winadd();
        gamelogicadd();
        themechange();
        SC1.setImageDrawable(d1);
        SC2.setImageDrawable(d2);
        // iv1.setImageDrawable(d4);
        Intent it2=getIntent();
        Bundle bt=it2.getExtras();

        //tlps=new Timelaps(maintimecount);
        if(bt!=null){
            //System.out.println("HI");
            comcount= (int)bt.get("comwincount");
            ucount=(int)bt.get("uwincount");
            gcount=(int)bt.get("globalcount");
            gametype=bt.getString("Gametype");
            //System.out.println(gcount);
            if (gametype.equals("Time")){
                if(gcount>0){
                    tlps=new Timelaps(maintimecount-(30000)-(gcount*5*1000));
                }else {tlps=new Timelaps(maintimecount-30000);}

            }else if(gametype.equals("Final")){
                tlps=new Timelaps(maintimecount-(30*1000));}
            else{
                tlps=new Timelaps(maintimecount);
            }
            //themechange();
            if((gcount%2)==0){

                play1();
                playgame();
            }else{

                play2();

            }
        }else{
            ucount = 0;
            comcount = 0;
            gcount=0;
            play1();
            playgame();
        }
System.out.println(gametype);




     scoremsg.setText(comcount+" : "+ucount);

    }
   public void player1winadd(){
       player1win.add("147");
       player1win.add("159");
       player1win.add("258");
       player1win.add("357");
       player1win.add("369");
       player1win.add("456");
       player1win.add("789");

   }
    public void player2winadd(){
        player2win.add("147");
        player2win.add("159");
        player2win.add("258");
        player2win.add("357");
        player2win.add("369");
        player2win.add("456");
        player2win.add("123");

    }
    public void gamelogicadd(){
        comlogic.add("123,789"); decesion.add("1,4");
        comlogic.add("234,579"); decesion.add("3,6");
        comlogic.add("246,578"); decesion.add("6,9");
        comlogic.add("249,378"); decesion.add("2,5");
        comlogic.add("249,678"); decesion.add("2,5");
        comlogic.add("459,678"); decesion.add("4,1");
        comlogic.add("459,378"); decesion.add("4,1");
        comlogic.add("459,278"); decesion.add("4,1");
        //////////////////////////////////////////////////////////////
        comlogic.add("246,179"); decesion.add("2,5");
        comlogic.add("246,379"); decesion.add("2,5");
        comlogic.add("246,789"); decesion.add("2,5");
      ///////////////////////////////// ///////////////////
        comlogic.add("249,178"); decesion.add("4,5");
        comlogic.add("259,148"); decesion.add("2,3");
        comlogic.add("259,478"); decesion.add("2,3");
        ///////////////////////////////////////////////////
        comlogic.add("246,589"); decesion.add("4,7");
        comlogic.add("267,189"); decesion.add("6,5");
        comlogic.add("267,489"); decesion.add("6,5");
        ///////////////////////////////////////////////////////
        comlogic.add("267,389"); decesion.add("6,5");
        comlogic.add("257,689"); decesion.add("2,1");
        comlogic.add("257,368"); decesion.add("2,1");
        //////////////////////////////////////////////////////

        comlogic.add("234,678"); decesion.add("2,5");
        comlogic.add("345,679"); decesion.add("5,8");
        comlogic.add("348,579"); decesion.add("3,6");
        ////////////////////////////////////////////////////
        comlogic.add("126,459"); decesion.add("2,3");
        comlogic.add("139,678"); decesion.add("1,4");
    }
    public void playgame()  {
        timr.cancel();
        timr.start();
        String str2;
        String str1=player1check+","+player2check;
        //System.out.println(player1check);
        int ck3=comlogic.indexOf(str1);
       // System.out.println(ck3);

        if(ck3>-1){

            str2=decesion.get(ck3);
            comswap(str2);

        }else{ if(player1check.equals("3,5,9")&& iv6.getDrawable().getConstantState().equals(d3.getConstantState()))
        {
            String ins="5,6";
            comswap(ins);
        }else if(player1check.equals("2,5,7")&& iv3.getDrawable().getConstantState().equals(d3.getConstantState()))
        {
            String ins="2,3";
            comswap(ins);
        }else if(player1check.equals("1,5,7")&& iv4.getDrawable().getConstantState().equals(d3.getConstantState()))
        {
            String ins="5,4";
            comswap(ins);
        }else if(player1check.equals("4,6,8")&& iv5.getDrawable().getConstantState().equals(d3.getConstantState()))
        {
            String ins="8,5";
            comswap(ins);
        }else{
             String K="1";
           // System.out.println(K);

            String [] viewobj=player1check.split("(?!^)");
            ArrayList<String> viewobj1=new ArrayList<String>();
            for(int i=0;i<viewobj.length;i++){
                viewobj1.add(viewobj[i]);
            }
            Collections.shuffle(viewobj1);
            for(int i=0;i<viewobj1.size();i++){
              //  System.out.println(viewobj[i]);
               String[] des=finaldes.get(Integer.valueOf(viewobj1.get(i))-1).split(",");
              for(int j=1;j<des.length;j++){
                 // System.out.println(des[j]);
                 if (imobj.get(Integer.valueOf(des[j])-1).getDrawable().getConstantState().equals(d3.getConstantState())&& K.equals("1")){
                     String ins=viewobj1.get(i)+","+des[j];
                    // System.out.println(ins);
                     comswap(ins);
                     K="23";
                 }
              }
            }
        }

        }

        play2();
        wincheck();
        display1.setImageDrawable(d2);
    }
    public void comswap(String str3){
        String []st=str3.split(",");
        imobj.get(Integer.valueOf(st[0]) -1).setImageDrawable(d3);
        imobj.get(Integer.valueOf(st[0]) -1).setOnDragListener(new dragdrop());
        imobj.get(Integer.valueOf(st[0]) -1).setOnTouchListener(null);
        imobj.get(Integer.valueOf(st[1]) -1).setImageDrawable(d1);
    }
public void makecomready(){
    for(int i = 0; i<myResources.size(); i++) {
        ImageView v = (ImageView) findViewById(myResources.get(i));


        if (v.getDrawable().getConstantState().equals(d1.getConstantState())) {
            v.setOnTouchListener(null);
            v.setOnDragListener(null);


        }
        if (v.getDrawable().getConstantState().equals(d2.getConstantState())) {
            v.setOnTouchListener(null);
            v.setOnDragListener(null);

        }
    }
}
   public class Timelaps{

       public  Timelaps( final long timemili){

           timetilfinish=timemili;

             timr = new CountDownTimer(timemili, 1000) { // adjust the milli seconds here

                public void onTick(long millisUntilFinished) {

                    display2.setText("" + String.format(FORMAT,

                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                    timetilgo=millisUntilFinished;
                }

                public void onFinish() {
                    display2.setText("done!");
                    playermsg="Timeout";
                    whowin=false;
                    gcount=gcount+1;
                    View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                    byte[] bt1=getScreenShot(rootView);
                    Toast.makeText(getApplicationContext(), "Player1 has won",
                            Toast.LENGTH_LONG).show();
                    Intent activityChangeIntent = new Intent(GameActivity.this, Paywin.class);
                    activityChangeIntent.putExtra("uwincount",ucount);
                    activityChangeIntent.putExtra("comwincount",comcount+1);
                    activityChangeIntent.putExtra("wincheck",whowin);
                    activityChangeIntent.putExtra("globalcount",gcount);
                    activityChangeIntent.putExtra("pic1",bt1);
                    activityChangeIntent.putExtra("msg1",playermsg);
                    activityChangeIntent.putExtra("Gametype",gametype);
                    startActivity(activityChangeIntent);

                    finish();
                }
            };
           timr.start();
        }

        public void onresume(){

            timr.cancel();


            tlps=new Timelaps(timetilfinish);

        }


   }
    public class Timelaps2{

        public  Timelaps2( final long timemili){

            timetilfinish=timemili;

            timr2 = new CountDownTimer(timemili, 1000) { // adjust the milli seconds here

                public void onTick(long millisUntilFinished) {



                    timetilgo=millisUntilFinished;
                }

                public void onFinish() {
                    playgame();
                }
            };
            timr2.start();
        }


    }
    public void themechange(){
        List<String> readtheme=new ArrayList<String>();
        readtheme=FileRW.fileread("Undotheme.txt",this.getBaseContext());
        String themevalue=readtheme.get(0);
        {
            if (themevalue.equals("one")) {
                rl1.setBackground(ContextCompat.getDrawable(this, R.drawable.g2));
                d1=ContextCompat.getDrawable(this,R.drawable.ball1);
                d2=ContextCompat.getDrawable(this,R.drawable.ball2);

            }
            if (themevalue.equals("two")) {
                //System.out.println("cool");
                rl1.setBackground(ContextCompat.getDrawable(this, R.drawable.flback));
                d1=ContextCompat.getDrawable(this,R.drawable.fl1);
                d2=ContextCompat.getDrawable(this,R.drawable.fl2);
            }
            if (themevalue.equals("three")) {
                rl1.setBackground(ContextCompat.getDrawable(this, R.drawable.game2));
                d1=ContextCompat.getDrawable(this,R.drawable.ball1);
                d2=ContextCompat.getDrawable(this,R.drawable.ball2);
            }
        }


        //rl1.setBackground(dw);
        for(int i = 0; i<imobj.size(); i++) {

            if(i>=0&&i<3){
                imobj.get(i).setImageDrawable(d1);
            }
            if(i>=6&&i<=8){
                imobj.get(i).setImageDrawable(d2);
            }
        }
    }

   public  void play1(){
       display1.setImageDrawable(d1);
       playemsg.setText("BOT");
       player1check="";
       player2check="";
       for(int i = 0; i<myResources.size(); i++) {
           ImageView v = (ImageView) findViewById(myResources.get(i));


           if (v.getDrawable().getConstantState().equals(d1.getConstantState())) {
               v.setOnTouchListener(new choiceontouch());
               v.setOnDragListener(null);
               player1check=player1check+(i+1);

           }
           if (v.getDrawable().getConstantState().equals(d2.getConstantState())) {
               v.setOnTouchListener(null);
               v.setOnDragListener(null);
               player2check=player2check+(i+1);
           }
               }

   }
    public  void play2(){
        player1check="";
        player2check="";
        display1.setImageDrawable(d2);
        playemsg.setText("Your Turn");
        for(int i = 0; i<myResources.size(); i++) {
            ImageView v = (ImageView) findViewById(myResources.get(i));
            if (v.getDrawable().getConstantState().equals(d1.getConstantState())) {
                v.setOnTouchListener(null);
                v.setOnDragListener(null);
                player1check=player1check+(i+1);
            }
            if (v.getDrawable().getConstantState().equals(d2.getConstantState())) {
                v.setOnTouchListener(new choiceontouch());
                v.setOnDragListener(null);
                player2check=player2check+(i+1);
            }
        }
    }

    public void wincheck(){
        win=1;

        int ck1=player1win.indexOf(player1check);
        int ck2=player2win.indexOf(player2check);
        if (ck1>-1){
            whowin=false;
            gcount=gcount+1;
            View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
            byte[] bt1=getScreenShot(rootView);
            Toast.makeText(getApplicationContext(), "Player1 has won",
                    Toast.LENGTH_LONG).show();
            Intent activityChangeIntent = new Intent(GameActivity.this, Paywin.class);
            activityChangeIntent.putExtra("uwincount",ucount);
            activityChangeIntent.putExtra("comwincount",comcount+1);
            activityChangeIntent.putExtra("wincheck",whowin);
            activityChangeIntent.putExtra("globalcount",gcount);
            activityChangeIntent.putExtra("pic1",bt1);
            activityChangeIntent.putExtra("msg1",playermsg);
            activityChangeIntent.putExtra("Gametype",gametype);
            startActivity(activityChangeIntent);

            finish();
        }
        if (ck2>-1){
            whowin=true;
            gcount=gcount+1;
            View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
            byte[] bt1=getScreenShot(rootView);
            Toast.makeText(getApplicationContext(), "Player2 has won",
                    Toast.LENGTH_LONG).show();
            Intent activityChangeIntent = new Intent(GameActivity.this, Paywin.class);
            activityChangeIntent.putExtra("uwincount",ucount+1);
            activityChangeIntent.putExtra("comwincount",comcount);
            activityChangeIntent.putExtra("wincheck",whowin);
            activityChangeIntent.putExtra("globalcount",gcount);
            activityChangeIntent.putExtra("pic1",bt1);
            activityChangeIntent.putExtra("msg1",playermsg);
            activityChangeIntent.putExtra("Gametype",gametype);
            startActivity(activityChangeIntent);
            win=2;
            finish();
        }

    }
    public static byte[] getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap=Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getHeight()*0.20),(int)(bitmap.getWidth()*0.20),true);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private final class choiceontouch implements View.OnTouchListener{


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if((event.getAction()==MotionEvent.ACTION_DOWN)&&((ImageView)v).getDrawable()!=null){
                ClipData cp=ClipData.newPlainText("","");
                View.DragShadowBuilder sb=new View.DragShadowBuilder(v);
                v.startDrag(cp,sb,v,0);
                return  true;

            }else {

                return false;
            }
        }

    }

    private class dragdrop implements View.OnDragListener{


        @Override
        public boolean onDrag(View v, DragEvent event) {
            ImageView view1=(ImageView)event.getLocalState();

            int des;
            des=imobj.indexOf(view1);
            String [] str=finaldes.get(des).split(",");
         switch (event.getAction()){

             case DragEvent.ACTION_DRAG_STARTED:

                for (int j=1;j<str.length;j++)
                {
                    int num=Integer.parseInt(str[j]);
                    if(imobj.get(num-1).getDrawable().getConstantState().equals(d3.getConstantState())){
                        ((CircleImageView)imobj.get(num-1)).setBorderWidth(20);
                        ((CircleImageView)imobj.get(num-1)).setBorderColor(Color.GREEN);


                    }
                }
                 break;
             case DragEvent.ACTION_DRAG_ENTERED:

                 break;
             case DragEvent.ACTION_DRAG_EXITED:

                 break;
             case DragEvent.ACTION_DROP:
                 ImageView view=(ImageView)event.getLocalState();
                 int cl=((CircleImageView)v).getBorderColor();
                 if(cl==Color.GREEN){
              Drawable pic=((ImageView)v).getDrawable();
                 ((ImageView)v).setImageDrawable(view.getDrawable());
                 view.setImageDrawable(pic);
                 view.setOnTouchListener(null);
                 view.setOnDragListener(new dragdrop());
                 v.setOnTouchListener(new choiceontouch());
                 v.setOnDragListener(null);
                 if(((ImageView)v).getDrawable().getConstantState().equals(d1.getConstantState())){

                     play2();
                     //display1.setImageDrawable(d2);
                     wincheck();
                 }
                 else {play1();
                     wincheck();
                     timr.cancel();
                     timr.start();
                     display1.setImageDrawable(d1);
                     for (int j=1;j<str.length;j++)
                     {
                         int num=Integer.parseInt(str[j]);


                         ((CircleImageView)imobj.get(num-1)).setBorderColor(Color.BLACK);
                         ((CircleImageView)imobj.get(num-1)).setBorderWidth(1);


                     }


                     makecomready();
                     if(win==1) {
                         Timelaps2 td = new Timelaps2(3000);
                     }

                 }
                 }
                 break;
             case DragEvent.ACTION_DRAG_ENDED:
                 for (int j=1;j<str.length;j++)
                 {
                     int num=Integer.parseInt(str[j]);


                         ((CircleImageView)imobj.get(num-1)).setBorderColor(Color.BLACK);
                     ((CircleImageView)imobj.get(num-1)).setBorderWidth(1);

                 }
                 break;
         }

            return true;
        }
    }
    private class pauseaction implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            timetilfinish=timetilgo;
            Intent en=new Intent(GameActivity.this,MainActivity.class);
            en.putExtra("requestCode", 2);
            startActivityForResult(en, 2);
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
    public void onResume(){
        //will be executed onResume
        super.onResume();

        tlps.onresume();
    }@Override
    protected void onStop() {
        super.onStop();
        timr.cancel();   }
    @Override
    protected void onPause() {
        super.onPause();
           }

}
