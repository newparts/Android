package com.iuliu.aplicatiethread;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button buttonStartThread;

    private Handler mainHandler = new Handler();

    private volatile boolean stopThread = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartThread = findViewById(R.id.button_start_thread);
    }

    public void startThread(View view) {
        stopThread = false;
        ExRun runnable = new ExRun(20);
        new Thread(runnable).start();
        /*3
        ExThread thread = new ExThread(10);
        thread.start();
        */
        /*4
        new Thread(new Runnable() {
            @Override
            public void run() {
                //merge
            }
        }).start();
        */
    }

    public void stopThread(View view) {
        stopThread = true;
    }

    class ExThread extends Thread {
        int seconds;

        ExThread(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() {
            for (int i = 0; i < seconds; i++) {
                Log.d(TAG, "Thread activ: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ExRun implements Runnable {
        int seconds;

        ExRun(int seconds) {
            this.seconds = seconds;
        }

        @Override
        public void run() {
            for (int i = 0; i < seconds; i++) {
                if (stopThread)
                    return;
                if (i == 5) {
                    /*1
                    Handler threadHandler = new Handler(Looper.getMainLooper());
                    threadHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("Merge");
                        }
                    });
                    */
                    /*2
                    buttonStartThread.post(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("Functioneaza");
                        }
                    });
                    */
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("Activ");
                        }
                    });
                }
                Log.d(TAG, "Thread in functiune: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
