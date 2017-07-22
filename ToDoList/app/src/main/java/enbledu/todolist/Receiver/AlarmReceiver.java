package enbledu.todolist.Receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import enbledu.todolist.R;

/**
 * Created by Administrator on 2017/7/22 0022.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("msg");
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        //广播中不能用alertdialog
/*
        AlertDialog.Builder builder = new Builder(context);
        builder.setTitle("提示");
        builder.setMessage("强制下线");
        builder.setCancelable(false);
        builder.setPositiveButton("ok", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });
        AlertDialog dialog = builder.create();

        dialog.getWindow()
                .setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

        dialog.show();*/
        MediaPlayer player;
        player = MediaPlayer.create(context, R.raw.back);
        player.setLooping(false);
        player.start();
    }


}