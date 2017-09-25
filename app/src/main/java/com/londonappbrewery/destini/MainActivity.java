package com.londonappbrewery.destini;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int TOP_BUTTON_PRESSED = 0;
    private final int BOTTOM_BUTTON_PRESSED = 1;

    // TODO: Steps 4 & 8 - Declare member variables here:
    private final StoryTier[] mStoryBank = new StoryTier[]
            {
                    new StoryTier(R.string.T1_Story, R.string.T1_Ans1, R.string.T1_Ans2), //tier 1
                    new StoryTier(R.string.T2_Story, R.string.T2_Ans1, R.string.T2_Ans2), //tier 2
                    new StoryTier(R.string.T3_Story, R.string.T3_Ans1, R.string.T3_Ans2), //tier 3
                    new StoryTier(R.string.T4_End, R.string.restart, 0), //tier 4
                    new StoryTier(R.string.T5_End, R.string.restart, 0), //tier 5
                    new StoryTier(R.string.T6_End, R.string.restart, 0) //tier 6
            };

    private int mStoryIndex = 0;
    private TextView mStoryTextView;
    private Button mButtonTop, mButtonBottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        mStoryTextView = (TextView) findViewById(R.id.storyTextView);
        mButtonTop = (Button) findViewById(R.id.buttonTop);
        mButtonBottom = (Button) findViewById(R.id.buttonBottom);


        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        mButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStory(TOP_BUTTON_PRESSED);
            }
        });

        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        mButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStory(BOTTOM_BUTTON_PRESSED);
            }
        });
    }

    public void updateStory(int buttonPressed) {
        String restartStory = getString(R.string.restart);

        if ((mStoryIndex == 0 || mStoryIndex == 1) && buttonPressed == TOP_BUTTON_PRESSED) {
            //executes if current story tier is at tier 1 or 2 and top button is pressed
            //Sets story to story tier 3
            mStoryIndex = 2;
        } else if (mStoryIndex == 2 && buttonPressed == TOP_BUTTON_PRESSED) {
            //executes if current story tier is at tier 3 and top button is pressed
            //Sets story to story tier 6
            mStoryIndex = 5;
        } else if (mStoryIndex == 2 && buttonPressed == BOTTOM_BUTTON_PRESSED) {
            //executes if current story tier is at tier 3 and bottom button is pressed
            //Sets story to story tier 5
            mStoryIndex = 4;
        } else if (mStoryIndex == 0 && buttonPressed == BOTTOM_BUTTON_PRESSED) {
            //executes if current story tier is at tier 1 and bottom button is pressed
            //Sets story to story tier 2
            mStoryIndex = 1;
        } else if (mStoryIndex == 1 && buttonPressed == BOTTOM_BUTTON_PRESSED) {
            //executes if current story tier is at tier 2 and bottom button is pressed
            //Sets story to story tier 4
            mStoryIndex = 3;
        } else if (mButtonTop.getText().toString().equals(restartStory)) {
            //Restarts story
            mStoryIndex = 0;
            mButtonBottom.setVisibility(View.VISIBLE);
        }

        int storyID = mStoryBank[mStoryIndex].getStoryId();
        int topButtonID = mStoryBank[mStoryIndex].getTopButtonId();
        int bottomButtonId = mStoryBank[mStoryIndex].getBottomButtonId();

        setViewText(storyID, topButtonID, bottomButtonId);
    }

    private void setViewText(int storyTextView, int buttonTop, int buttonBottom) {
        mStoryTextView.setText(storyTextView);
        mButtonTop.setText(buttonTop);

        if (buttonBottom == 0) {
            mButtonBottom.setVisibility(View.GONE);
        } else {
            mButtonBottom.setText(buttonBottom);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Exit Destini");
        alert.setCancelable(false);
        alert.setMessage("Are you sure you want to exit?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}
