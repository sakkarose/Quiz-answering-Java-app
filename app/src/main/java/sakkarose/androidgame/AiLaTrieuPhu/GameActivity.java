package sakkarose.androidgame.AiLaTrieuPhu;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

import sakkarose.androidgame.AiLaTrieuPhu.Model.Question;
import sakkarose.androidgame.AiLaTrieuPhu.Handler.Database;

public class GameActivity extends AppCompatActivity
{

    private TextView tvcontentquestion, tvquestion, tvanswerA, tvanswerB, tvanswerC, tvanswerD;
    MediaPlayer mp;

    private static CDRunnable CDRun;
    private static Handler CDHandler;

    Database appDatabase;

    Dialog dg, dg1;

    int socauhientai = 0, socauDung = 0, time = 30;
    ArrayList<Question> questionList;



    private void findViewsByIds()
    {
        tvcontentquestion = (TextView) findViewById(R.id.tv_content_question);
        tvquestion = (TextView) findViewById(R.id.tv_question);
        tvanswerA = (TextView) findViewById(R.id.tv_answer1);
        tvanswerB = (TextView) findViewById(R.id.tv_answer2);
        tvanswerC = (TextView) findViewById(R.id.tv_answer3);
        tvanswerD = (TextView) findViewById(R.id.tv_answer4);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        }catch (IOException e){
        }

        CDHandler = new Handler();
        CDRun = new CDRunnable();

        playbgm(R.raw.);

        final Dialog dg = new Dialog(GameActivity.this, R.style.custom_dialog);
        dg.setTitle("Chú ý !");
        dg.getWindow().setBackgroundDrawableResource(R.drawable.dialog_box);
        dg.setContentView(R.layout.dialog_ready);

        TextView tv = (TextView) dg.findViewById(R.id.textDialog);
        tv.setText("Bạn đã sẵn sàng cùng chơi với chúng tôi?");
        dg.show();

        final Button btnTuChoi = (Button) dg.findViewById(R.id.btn_tuchoi);
        final Button btnHuy = (Button) dg.findViewById(R.id.btn_huy);

        btnTuChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dg.dismiss();

                Dialog dg1 = new Dialog(GameActivity.this, R.style.custom_dialog);
                dg1.setTitle("Chú ý !");
                dg1.getWindow().setBackgroundDrawableResource(R.drawable.dg_box2);
                dg1.setContentView(R.layout.);
            }
        });






}
