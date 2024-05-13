package ufv.joseignacio.myimageandsoundapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {

    ImageView image;

    MediaPlayer sonido;

    Button changeImageButton;

    MediaController controles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainScreen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        image = findViewById(R.id.imagenPrincipal);

        sonido = MediaPlayer.create(this, R.raw.homercama);
        controles = new MediaController(this);
        controles.setMediaPlayer(this);
        controles.setAnchorView(findViewById(R.id.mainScreen));

        image.setImageResource(R.drawable.simpsonslogo);

        changeImageButton = findViewById(R.id.changeImageButton);

        changeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cambio la imagen
                Random r = new Random();
                int randomNumber = r.nextInt(4-1)+1;

                switch (randomNumber){
                    case 1:
                        image.setImageResource(R.drawable.simpsons1);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.simpsons2);
                        break;
                    case 3:
                        image.setImageResource(R.drawable.simpsons3);
                        break;
                    default:
                        break;
                }

            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        controles.show(6000);
        return false;
    }

    @Override
    public void start() {sonido.start();}

    @Override
    public void pause() {sonido.pause();}

    @Override
    public int getDuration() {return sonido.getDuration();}

    @Override
    public int getCurrentPosition() {return sonido.getCurrentPosition();}

    @Override
    public void seekTo(int pos) {sonido.seekTo(pos);}

    @Override
    public boolean isPlaying() {return sonido.isPlaying();}

    @Override
    public int getBufferPercentage() {return 0;}

    @Override
    public boolean canPause() {return true;}

    @Override
    public boolean canSeekBackward() {return true;}

    @Override
    public boolean canSeekForward() {return true;}

    @Override
    public int getAudioSessionId() {return 0;}
}