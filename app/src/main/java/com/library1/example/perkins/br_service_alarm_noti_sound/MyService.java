package com.library1.example.perkins.br_service_alarm_noti_sound;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * Created by Perkins on 4/7/2016.
 */
public class MyService extends Service implements SoundPool.OnLoadCompleteListener {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //send a notification
        doNoti();
        //play a sound
        playSound();
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private static final int MAX_STREAMS = 10;
    private static final float LEFTVOLUME = 1;
    private static final float RIGHTVOLUME = 1;
    private static final int PRIORITY = 0;
    private static final int LOOPFOREVER = -1;
    private static final int LOOPNOT = 0;
    private static final float RATE = 1;
    private static final int UNINITIALIZED = -1;
    private static final String TAG = "ActivitySound";
    SoundPool sp = null;

    int dogsound;
    private void playSound() {
        //get soundpool object
        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);    //srcQuality Currently has no effect. Use 0 for the default.

        //listen for when following loads are done
        sp.setOnLoadCompleteListener(this);

        //load our sounds
        dogsound = sp.load(this, R.raw.dog, 0);

    }

    private static final int MYNOTIFICATION = 3;
    private void doNoti() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification noti = new Notification.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Just a Notice")
                .setSmallIcon(R.drawable.smallpic)
                .setOngoing(false)						//true only dismissable by app
                .setProgress(100,10,true )				//show a progress bar
                .build();

        // Hide the notification after its selected
        //noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(MYNOTIFICATION, noti);
     }

    /**
     * Return the communication channel to the service.  May return null if
     * clients can not bind to the service.  The returned
     * {@link IBinder} is usually for a complex interface
     * that has been <a href="{@docRoot}guide/components/aidl.html">described using
     * aidl</a>.
     * <p/>
     * <p><em>Note that unlike other application components, calls on to the
     * IBinder interface returned here may not happen on the main thread
     * of the process</em>.  More information about the main thread can be found in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html">Processes and
     * Threads</a>.</p>
     *
     * @param intent The Intent that was used to bind to this service,
     *               as given to {@link Context#bindService
     *               Context.bindService}.  Note that any extras that were included with
     *               the Intent at that point will <em>not</em> be seen here.
     * @return Return an IBinder through which clients can call on to the
     * service.
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Called when a sound has completed loading.
     *
     * @param soundPool SoundPool object from the load() method
     * @param sampleId  the sample ID of the sound loaded.
     * @param status    the status of the load operation (0 = success)
     */

    //use this int to stop the sound if its looping forever
    int dogClobberID = 0;
    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        //create stream, the int returned is the value you use to clobber it
        dogClobberID = sp.play(dogsound, LEFTVOLUME, RIGHTVOLUME, PRIORITY, LOOPNOT, RATE);
     }
}
