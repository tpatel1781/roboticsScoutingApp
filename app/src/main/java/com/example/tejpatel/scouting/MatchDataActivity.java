package com.example.tejpatel.scouting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MatchDataActivity extends AppCompatActivity {
    private Context context;
    String fileName = "scoutingData";
    String data;
    FileOutputStream outputStream;

    int numGears = 0;
    int numLowGoals = 0;
    int numHighGoals = 0;
    String notes;
    String startPosition = "Never entered";

    // 0 means not checked, 1 means checked
    int placedGear = 0;
    int highGoal = 0;
    int lowGoal = 0;

    String matchNumber;
    String robotNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_data);

        context = this;

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
        final EditText notesEditText = (EditText) findViewById(R.id.notes_edit_text);

        RadioButton leftPositionRadioButton = (RadioButton) findViewById(R.id.left_position_button);
        RadioButton centerPositionRadioButton = (RadioButton) findViewById(R.id.center_position_button);
        RadioButton rightPositionRadioButton = (RadioButton) findViewById(R.id.right_position_button);

        CheckBox placedGearCheckbox = (CheckBox) findViewById(R.id.placed_gear_checkbox);
        CheckBox highGoalCheckbox = (CheckBox) findViewById(R.id.high_goal_checkbox);
        CheckBox lowGoalCheckBox = (CheckBox) findViewById(R.id.low_goal_checkbox);

        // OnClickListeners for adding and removing
        addGearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numGearsTextView.setText(Integer.toString(++numGears));
            }
        });

        removeGearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numGears == 0) {
                    numGearsTextView.setText(Integer.toString(numGears));
                } else {
                    numGearsTextView.setText(Integer.toString(--numGears));
                }
            }
        });

        // OnClickListeners for adding and removing
        addLowGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numLowGoalsTextView.setText(Integer.toString(++numLowGoals));
            }
        });

        removeLowGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numLowGoals == 0) {
                    numLowGoalsTextView.setText(Integer.toString(numLowGoals));
                } else {
                    numLowGoalsTextView.setText(Integer.toString(--numLowGoals));
                }
            }
        });

        // OnClickListeners for adding and removing high goals
        addHighGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numHighGoalsTextView.setText(Integer.toString(++numHighGoals));
            }
        });

        removeHighGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numHighGoals == 0) {
                    numHighGoalsTextView.setText(Integer.toString(numHighGoals));
                } else {
                    numHighGoalsTextView.setText(Integer.toString(--numHighGoals));
                }
            }
        });

        // OnClickListener for end match button
        // Passes data to generateString method
        endMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes = notesEditText.getText().toString();
                generateString(startPosition, placedGear, highGoal, lowGoal, numGears, numLowGoals, numHighGoals, notes);

                Toast.makeText(context, "Match finished, data recorded", Toast.LENGTH_SHORT).show();
            }
        });

        // OnClickListeners for left, center, and right starting position radio buttons
        leftPositionRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosition = "LEFT";
            }
        });

        centerPositionRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosition = "CENTER";
            }
        });

        rightPositionRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosition = "RIGHT";
            }
        });


        placedGearCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placedGear = 1;
            }
        });

        highGoalCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highGoal = 1;
            }
        });

        lowGoalCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowGoal = 1;
            }
        });

        // TODO Add value for climbed checkbox in CSV String

    }

    // Generates comma separated string with number of gears, low goals, and high goals
    // The string is then passed to the CreateCSVFile method
    public void generateString(String startPosition, int placedGear, int highGoal, int lowGoal, int numGears, int numLowGoals, int numHighGoals, String matchNotes) {
        data = startPosition + ", " + Integer.toString(placedGear) + ", " + Integer.toString(highGoal) + ", " + Integer.toString(lowGoal) + ", " + Integer.toString(numGears) + ", " + Integer.toString(numLowGoals) + ", " + Integer.toString(numHighGoals) + ", " + matchNotes;
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
