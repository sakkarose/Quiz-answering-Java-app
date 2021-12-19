package sakkarose.androidgame.AiLaTrieuPhu;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import sakkarose.androidgame.AiLaTrieuPhu.Adapter.ActivityAnimation;

public class AddQuestionActivity extends AppCompatActivity
{

    ImageView ivback;
    EditText etquestion, etanswerA, etanswerB, etanswerC, etanswerD;
    MediaPlayer mp;

    private void findViewsByIds()
    {
        etquestion = (EditText) findViewById(R.id.et_content_question);
        etanswerA = (EditText) findViewById(R.id.et_answer1);
        etanswerB = (EditText) findViewById(R.id.et_answer2);
        etanswerC = (EditText) findViewById(R.id.et_answer3);
        etanswerD = (EditText) findViewById(R.id.et_answer4);

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

    ivback.setOnClickListener()
    {
        ActivityAnimation anim = new ActivityAnimation();
        anim.unzoomAnimation(MainActivity.this);
    }




    @Override
    public void onClick(View v) {

    }
}
