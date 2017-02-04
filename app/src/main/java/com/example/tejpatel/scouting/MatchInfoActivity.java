package com.example.tejpatel.scouting;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rengwuxian.materialedittext.MaterialEditText;

public class MatchInfoActivity extends AppCompatActivity {
    private Context context;
    public String matchNumber;
    public String robotNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);

        context = this;

        ImageView logoImageView = (ImageView) findViewById(R.id.logo_image_view);
        // Set logo opacity to 5%
        logoImageView.setAlpha(0.05f);

        final MaterialEditText matchNumberEditText = (MaterialEditText) findViewById(R.id.match_number_edit_text);
        final MaterialEditText robotNumberEditText = (MaterialEditText) findViewById(R.id.robot_number_edit_text);
        Button startMatchButton = (Button) findViewById(R.id.start_match_button);

        // Button will set match and robot numbers equal to string variables and pass it to the MatchDataActivity
        startMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // matchNumber.equals(matchNumberEditText.getText().toString());
                // robotNumber.equals(robotNumberEditText.getText().toString());

                Intent myIntent = new Intent(context, MatchDataActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
