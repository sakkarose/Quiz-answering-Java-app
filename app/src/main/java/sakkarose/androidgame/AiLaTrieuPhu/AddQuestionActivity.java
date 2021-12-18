package sakkarose.androidgame.AiLaTrieuPhu;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AddQuestionActivity extends AppCompatActivity
{

    ImageView ivback;
    TextView tvquestion, tvanswerA, tvanswerB, tvanswerC, tvanswerD;
    MediaPlayer mp;

    private void findViewsByIds()
    {
        tvquestion = (TextView) findViewById(R.id.tv_question);
        tvanswerA = (TextView) findViewById(R.id.tv_answer1);
        tvanswerB = (TextView) findViewById(R.id.tv_answer2);
        tvanswerC = (TextView) findViewById(R.id.tv_answer3);
        tvanswerD = (TextView) findViewById(R.id.tv_answer4);

        ivback = (ImageView) findViewById(R.id.iv_back);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //set layout full man hinh vi khac nhau dpi giua cac may
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewsByIds();
        setContentView(R.layout.activity_add_question);
    }
}
