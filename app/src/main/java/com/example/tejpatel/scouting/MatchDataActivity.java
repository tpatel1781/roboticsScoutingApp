package com.example.tejpatel.scouting;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;

public class MatchDataActivity extends AppCompatActivity {
    private Context context;
    String fileName = "scoutingData";
    String data;
    FileOutputStream outputStream;

    int numGears = 0;
    int numLowGoals = 0;
    int numHighGoals = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_data);

        final TextView numGearsTextView = (TextView) findViewById(R.id.num_gears_text_view);
        numGearsTextView.setText(Integer.toString(numGears));

        Button addGearButton = (Button) findViewById(R.id.add_gear_button);
        Button removeGearButton = (Button) findViewById(R.id.remove_gear_button);

        final TextView numLowGoalsTextView = (TextView) findViewById(R.id.num_low_goals_text_view);
        numLowGoalsTextView.setText(Integer.toString(numLowGoals));

        Button addLowGoalButton = (Button) findViewById(R.id.add_low_goal_button);
        Button removeLowGoalButton = (Button) findViewById(R.id.remove_low_goal_button);

        final TextView numHighGoalsTextView = (TextView) findViewById(R.id.num_high_goals_text_view);
        numHighGoalsTextView.setText(Integer.toString(numHighGoals));

        Button addHighGoalButton = (Button) findViewById(R.id.add_high_goal_button);
        Button removeHighGoalButton = (Button) findViewById(R.id.remove_high_goal_button);

        Button endMatchButton = (Button) findViewById(R.id.end_match_button);

        // Set of OnClickListeners for adding, removing, and undoing gears
        addGearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numGearsTextView.setText(Integer.toString(++numGears));
            }
        });

        removeGearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numGearsTextView.setText(Integer.toString(--numGears));
            }
        });

        // Set of OnClickListeners for adding, removing, and undoing low goals
        addLowGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numLowGoalsTextView.setText(Integer.toString(++numLowGoals));
            }
        });

        removeLowGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numLowGoalsTextView.setText(Integer.toString(--numLowGoals));
            }
        });

        // Set of OnClickListeners for adding, removing, and undoing high goals
        addHighGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numHighGoalsTextView.setText(Integer.toString(++numHighGoals));
            }
        });

        removeHighGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numHighGoalsTextView.setText(Integer.toString(--numHighGoals));
            }
        });

        endMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateString(numGears, numLowGoals, numHighGoals);
            }
        });
    }

    // Generates comma separated string with number of gears, low goals, and high goals
    // The string is then passed to the CreateCSVFile method
    public void generateString(int numGears, int numLowGoals, int numHighGoals) {
        data = Integer.toString(numGears) + ", " + Integer.toString(numLowGoals) + ", " + Integer.toString(numHighGoals);
        Log.i("csvData", data);
        CreateCSVFile(context, fileName, data);
    }

    // Creates CSV file and stores comma separated string in it
    public void CreateCSVFile(Context context, String csvFileName, String csvData) {
        try {
            outputStream = openFileOutput(csvFileName, Context.MODE_PRIVATE);
            outputStream.write(csvData.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
