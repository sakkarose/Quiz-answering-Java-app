package sakkarose.androidgame.AiLaTrieuPhu;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import sakkarose.androidgame.AiLaTrieuPhu.Adapter.ActivityAnimation;
import sakkarose.androidgame.AiLaTrieuPhu.Model.Question;
import sakkarose.androidgame.AiLaTrieuPhu.Handler.Database;

public class GameActivity extends AppCompatActivity {

    private TextView tvtime, tvcontentquestion, tvquestion, tvanswerA, tvanswerB, tvanswerC, tvanswerD;
    MediaPlayer mp;



    private static CDRunnable CDRun;
    private static Handler CDHandler;
    boolean isRunning = false;

    Database appDatabase;

    Dialog dg, dg1;

    //Thanh bên cạnh để hiện vị trí và tiền thưởng
    //DrawerLayout drawerLayout;
    //NavigationView navi;
    //String indicator;
    //AVLoadingIndicatorView avi;

    int socauhientai = 0, socauDung = 0, time = 30, trueCase, idTC;
    ArrayList<Question> questionList;


    private void findViewsByIds() {
        tvcontentquestion = (TextView) findViewById(R.id.tv_content_question);
        tvquestion = (TextView) findViewById(R.id.tv_question);
        tvanswerA = (TextView) findViewById(R.id.tv_answer1);
        tvanswerB = (TextView) findViewById(R.id.tv_answer2);
        tvanswerC = (TextView) findViewById(R.id.tv_answer3);
        tvanswerD = (TextView) findViewById(R.id.tv_answer4);

        tvtime = (TextView) findViewById(R.id.tv_time);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set layout full man hinh vi khac nhau dpi giua cac may
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game);

        findViewsByIds();

        questionList = new ArrayList<Question>();
        try {
            appDatabase = new Database(this);
            questionList = appDatabase.getData();
        } catch (IOException e) {
        }

        //tvanswerA.setOnClickListener(this);


        CDHandler = new Handler();
        CDRun = new CDRunnable();

        playbgm(R.raw.ready);

        final Dialog dg = new Dialog(GameActivity.this, R.style.custom_dialog);
        dg.setTitle("Chú ý !");
        dg.getWindow().setBackgroundDrawableResource(R.drawable.dialog_box);
        dg.setContentView(R.layout.dialog_ready);

        TextView tv = (TextView) dg.findViewById(R.id.textDialog);
        tv.setText("Bạn đã sẵn sàng ?");
        dg.show();

        final Button btnDongY = (Button) dg.findViewById(R.id.btn_dongy);
        final Button btnHuy = (Button) dg.findViewById(R.id.btn_huy);

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dg.dismiss();
                    socauhientai++;
                    playbgm(R.raw.start);
                    Thread.sleep(2000);


                } catch (Exception e) {
                }
            }
        });


    }

    public void showQuestion()
    {
        trueCase();
        //playbgm(BGM_Question[socauhientai - 1]);
        tvquestion.setText(socauhientai);
        tvcontentquestion.setText(questionList.get(socauhientai - 1).Question);

        tvanswerA.setText(questionList.get(socauhientai - 1).CaseA);
        tvanswerB.setText(questionList.get(socauhientai - 1).CaseB);
        tvanswerC.setText(questionList.get(socauhientai - 1).CaseC);
        tvanswerD.setText(questionList.get(socauhientai - 1).CaseD);

        time = 30;
        tvtime.setText(time + "");
        time();
    }

    public void trueCase()
    {
        idTC = questionList.get(socauhientai - 1).TrueCase;
        switch (idTC)
        {
            case 1:
                trueCase = R.id.tv_answer1;
                break;
            case 2:
                trueCase = R.id.tv_answer2;
                break;
            case 3:
                trueCase = R.id.tv_answer3;
                break;
            case 4:
                trueCase = R.id.tv_answer4;
                break;
        }
    }

    private void showCD()
    {
        time = 30;
        CDHandler.removeCallbacks(CDRun);
        CDHandler.postDelayed(CDRun, 1000);
    }

    private class CDRunnable implements Runnable
    {
        @Override
        public void run()
        {
            handlerCD();
        }
    }

    private void handlerCD()
    {
        time--;
        if(time == 0)
        {
            isRunning = false;
            mp.stop();
            //playbgm(R.raw.out_of_time);

            switch (trueCase)
            {
                case R.id.tv_answer1:
                    playbgm(R.raw.lose_answer_a);
                    break;

                case R.id.tv_answer2:
                    playbgm(R.raw.lose_answer_b);
                    break;

                case R.id.tv_answer3:
                    playbgm(R.raw.lose_answer_c);
                    break;

                case R.id.tv_answer4:
                    playbgm(R.raw.lose_answer_d);
                    break;
            }
            try{
                Thread.sleep(2000);
                final Dialog dg = new Dialog(GameActivity.this);
                dg.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dg.setContentView(R.layout.dialog_finish);

                TextView tv = (TextView) dg.findViewById(R.id.textDialog);
                TextView tv_caudung = (TextView) dg.findViewById(R.id.textDialog_sc);
                tv.setText("Khá đáng tiếc ! Bạn đã thua.");
                tv_caudung.setText(socauhientai - 1);
                dg.setCancelable(true);
                dg.show();

                Button btn_ok = (Button) dg.findViewById(R.id.btn_ok_finish);
                btn_ok.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dg.dismiss();
                        goMainAct();
                    }
                });
                tvquestion.setText("");
                tvanswerA.setText("");
                tvanswerB.setText("");
                tvanswerC.setText("");
                tvanswerD.setText("");
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            tvtime.setText(time + "");
            CDHandler.removeCallbacks(CDRun);
        }

        else{
            isRunning = true;
            tvtime.setText(time + "");
            CDHandler.postDelayed(CDRun, 1000);
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        isRunning = false;
        CDHandler.removeCallbacks(CDRun);
        mp.stop();
    }

    public void time()
    {
        showCD();
    }

    public void stop() {
        mp.stop();
        try {
            Thread.sleep(2000);

            final Dialog dg = new Dialog(GameActivity.this, R.style.custom_dialog);
            dg.setContentView(R.layout.dialog_finish);
            dg.setTitle("Thông báo !");
            dg.getWindow().setBackgroundDrawableResource(R.drawable.dialog_box);

            TextView tv = (TextView) dg.findViewById(R.id.textDialog);
            TextView tv_socaudung = (TextView) dg.findViewById(R.id.textDialog_socaudung);

            tv.setText("Bạn đã dừng cuộc chơi ! Cảm ơn bạn đã tham gia.");
            tv_socaudung.setText(socauhientai - 1);
            dg.setCancelable(false);
            dg.show();

            Button btn_ok = (Button) dg.findViewById(R.id.btn_ok_finish);
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dg.dismiss();
                    goMainAct();
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void playbgm(int type) {
        mp = MediaPlayer.create(this, type);
        mp.start();
    }

    public void playbgm_loop(int type) {
        mp = MediaPlayer.create(this, type);
        mp.setLooping(true);
        mp.start();
    }

    public void goMainAct()
    {
        mp.stop();
        finish();
        try {
            ActivityAnimation anim = new ActivityAnimation();
            anim.unzoomAnimation(GameActivity.this);
        }catch (Exception e){}
    }

    @Override
    public void onBackPressed()
    {
        goMainAct();
        super.onBackPressed();
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        if(isRunning == true) {
            CDRun.run();
        }
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        CDHandler.removeCallbacks(CDRun);
        super.onPause();
    }

    @Override
    public void onStop()
    {
        mp.stop();
        super.onStop();
    }
}


