package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int scoreTeam1 = 0;
    private int scoreTeam2 = 0;

    private TextView scoreView1;
    private TextView scoreView2;

    private static final String STATE_SCORE_1 = "state_score_team_1";
    private static final String STATE_SCORE_2 = "state_score_team_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton team1Minus = findViewById(R.id.minusTop);
        ImageButton team1Plus = findViewById(R.id.plusTop);
        ImageButton team2Minus = findViewById(R.id.minusBottom);
        ImageButton team2Plus = findViewById(R.id.plusBottom);

        scoreView1 = findViewById(R.id.score1);
        scoreView2 = findViewById(R.id.score2);

        team1Minus.setOnClickListener(v -> decreaseScoreTeam1());
        team1Plus.setOnClickListener(v -> increaseScoreTeam1());
        team2Minus.setOnClickListener(v -> decreaseScoreTeam2());
        team2Plus.setOnClickListener(v -> increaseScoreTeam2());

        if (savedInstanceState != null) {
            int scoreTeam1Value = savedInstanceState.getInt(STATE_SCORE_1);
            int scoreTeam2Value = savedInstanceState.getInt(STATE_SCORE_2);

            scoreView1.setText(String.valueOf(scoreTeam1Value));
            scoreView2.setText(String.valueOf(scoreTeam2Value));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu from XML
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //Change the label of the menu based on the state of the app
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void increaseScoreTeam1() {
        scoreTeam1++;
        scoreView1.setText(String.valueOf(scoreTeam1));
    }

    public void decreaseScoreTeam1() {
        scoreTeam1--;
        scoreView1.setText(String.valueOf(scoreTeam1));
    }

    public void increaseScoreTeam2() {
        scoreTeam2++;
        scoreView2.setText(String.valueOf(scoreTeam2));
    }

    public void decreaseScoreTeam2() {
        scoreTeam2--;
        scoreView2.setText(String.valueOf(scoreTeam2));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //Save the scores
        outState.putInt(STATE_SCORE_1, scoreTeam1);
        outState.putInt(STATE_SCORE_2, scoreTeam2);
        super.onSaveInstanceState(outState);
    }
}