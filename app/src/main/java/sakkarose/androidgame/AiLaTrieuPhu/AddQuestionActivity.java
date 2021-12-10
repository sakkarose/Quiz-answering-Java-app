package sakkarose.androidgame.AiLaTrieuPhu;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class AddQuestionActivity extends AppCompatActivity
{


    MediaPlayer mp;

    private void findViewsByIds()
    {



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
