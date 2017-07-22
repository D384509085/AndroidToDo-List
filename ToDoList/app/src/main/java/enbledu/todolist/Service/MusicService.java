package enbledu.todolist.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import enbledu.todolist.R;

/**
 * Created by Administrator on 2017/7/22 0022.
 */

public class MusicService extends Service {
    private MediaPlayer player;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        player = MediaPlayer.create(this, R.raw.back);
        player.setLooping(true);
        player.start();
        return null;
    }


    public void onDestroy()
    {
        super.onDestroy();
        if(player != null){
            player.stop();
        }
    }

}
