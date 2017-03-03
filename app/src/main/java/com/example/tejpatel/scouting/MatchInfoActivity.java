package com.example.tejpatel.scouting;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rengwuxian.materialedittext.MaterialEditText;

public class MatchInfoActivity extends AppCompatActivity {
    private Context context;
    public String matchNumber;
    public String teamNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);

        context = this;

        final MaterialEditText matchNumberEditText = (MaterialEditText) findViewById(R.id.match_number_edit_text);
        final MaterialEditText robotNumberEditText = (MaterialEditText) findViewById(R.id.robot_number_edit_text);
        Button startMatchButton = (Button) findViewById(R.id.start_match_button);

        // Button will set match and robot numbers equal to string variables and pass it to the MatchDataActivity
        startMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchNumber = matchNumberEditText.getText().toString();
                teamNumber = robotNumberEditText.getText().toString();

                Intent myIntent = new Intent(context, MatchDataActivity.class);
                Bundle extras = new Bundle();
                extras.putString("MATCH_NUMBER", matchNumber);
                extras.putString("TEAM_NUMBER", teamNumber);
                myIntent.putExtras(extras);

                Log.i("matchNumber", matchNumber);
                Log.i("robotNumber", teamNumber);

                startActivity(myIntent);
            }
        });
    }
}
