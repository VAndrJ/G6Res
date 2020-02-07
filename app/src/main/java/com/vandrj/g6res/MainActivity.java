package com.vandrj.g6res;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runShellCommand("wm size reset\nwm density reset", v);
            }
        });
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runShellCommand("wm size 1260x2520\nwm density 560", v);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runShellCommand("wm size 1080x2160\nwm density 480", v);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runShellCommand("wm size 900x1800\nwm density 400", v);
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runShellCommand("wm size 720x1440\nwm density 320", v);
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runShellCommand("wm size 540x1080\nwm density 240", v);
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runShellCommand("wm size 360x720\nwm density 160", v);
            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MARK: - Intermediate step to avoid elements layout bug.
                // MARK: - Some shit with density under 100 happens.
                runShellCommand("wm size 720x1440\nwm density 320\nwm size 225x450\nwm density 100", v);
            }
        });
    }

    private void runShellCommand(String command, View v) {
        try {
            Process sh = Runtime.getRuntime().exec("su", null, null);
            OutputStream os = sh.getOutputStream();
            byte[] mScreenBuffer = command.getBytes();
            os.write(mScreenBuffer);
            os.flush();
            os.close();
            sh.waitFor();
        } catch (InterruptedException | IOException e) {
            Snackbar.make(v, e.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
