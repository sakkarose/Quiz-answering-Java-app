package sakkarose.androidgame.AiLaTrieuPhu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import sakkarose.androidgame.AiLaTrieuPhu.R;

public class MainActivity extends AppCompatActivity
{



    private ImageButton btnhuongdan;

    private Button btnbatdau, btnthemcauhoi, btnthoat;

    MediaPlayer mp;

    private void findViewsByIds()
    {

        btnbatdau = (Button) findViewById(R.id.btn_batdau);
        btnhuongdan = (ImageButton) findViewById(R.id.btn_huongdan);
        btnthemcauhoi = (Button) findViewById(R.id.btn_themcauhoi);
        btnthoat = (Button) findViewById(R.id.btn_thoat);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //set layout full man hinh vi khac nhau dpi giua cac may
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewsByIds();
        setContentView(R.layout.activity_main);
        playbgm(R.raw.main_theme);

        btnbatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
                try
                {
                    ActivityAnimator anim = new ActivityAnimator();
                    anim.unzoomAnimation(MainActivity.this);
                }
                catch (Exception e) {
                }
            }
        });

        btnhuongdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this, R.style.custom_dialog);

                dialog.setContentView(R.layout.dialog_intro);
                dialog.setTitle("Hướng dẫn");
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_box);
                TextView text = (TextView) dialog.findViewById(R.id.textIntro);
                text.setText(R.string.bat_dau);

                dialog.show();


            }
        });

    }

    public void playbgm(int type)
    {
        mp = MediaPlayer.create(this,type);
        mp.setLooping(true);
        mp.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mp.stop();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        playbgm(R.raw.main_theme);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}