package com.example.shop;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NotificationJobService extends JobService {
    private NotificationHandler mNotificationHandler;

    @Override
    public boolean onStartJob(JobParameters params) {
        mNotificationHandler = new NotificationHandler(getApplicationContext());
        mNotificationHandler.send("Vásároljon nálunk!");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
