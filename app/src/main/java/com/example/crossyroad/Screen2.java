package com.example.crossyroad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Screen2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String difficultyLevel = "Not selected";
    private String sprite = "Not selected";
    private AnimationDrawable logoAnimation;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        difficultyLevel = (String) parent.getItemAtPosition(pos);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        difficultyLevel = "Not selected";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        ImageView imageView = (ImageView) findViewById(R.id.logoAnimation);
        imageView.setBackgroundResource(R.drawable.logo_animation);

        logoAnimation = (AnimationDrawable) imageView.getBackground();
        logoAnimation.start();


        TextView name = (TextView) findViewById(R.id.name); //variable from starting page name entry

        Button loginBtn = (Button) findViewById(R.id.button); // start page button

        Spinner spinnerDifficulty = (Spinner) findViewById(R.id.spinny); //Dropdown difficulty list

        TextView charSel = (TextView) findViewById(R.id.character_select); // character select text

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item); //creates the array
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerDifficulty.setAdapter(adapter); //assigns the adapter to spinny

        // Store difficulty that the user entered
        spinnerDifficulty.setSelection(0, false);
        spinnerDifficulty.setOnItemSelectedListener(this);

        // Store result of sprite selection
        RadioGroup sprites = (RadioGroup) findViewById(R.id.spriteButtons);
        sprites.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);

                switch (rb.getId()) {
                    case R.id.char1:
                        sprite = "Orange Tutu Buzz";
                        break;

                    case R.id.char2:
                        sprite = "Green Bow Buzz";
                        break;

                    case R.id.char3:
                        sprite = "Classic Buzz";
                        break;

                    default:
                        sprite = "Not selected";
                        break;
                }
                charSel.setText(sprite);
            }
        });
        /*
        -------NEEDS TO BE IMPLEMENTED FOR SPRINT 1----------------
        ADD CODE TO DISPLAY THE DIFFICULTY AND SPRITE SELECTION HERE:
        Difficulty is stored in String variable difficultyLevel
        Sprite is stored in String variable sprite
         */
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checks the requirements of the name to be valid
                if (!PlayerVerification.nameIsValid(name.getText().toString())) {
                    Toast.makeText(Screen2.this, "Invalid Name",
                            Toast.LENGTH_SHORT).show();
                } else if (!PlayerVerification.difficultyIsValid(difficultyLevel)) {
                    Toast.makeText(Screen2.this, "Difficulty Not Chosen",
                            Toast.LENGTH_SHORT).show();
                } else if (!PlayerVerification.spriteChosen(sprite)) {
                    Toast.makeText(Screen2.this, "Sprite Not Chosen", Toast.LENGTH_SHORT).show();
                } else {
                    Intent toThirdPage = new Intent(Screen2.this, Screen3.class);
                    toThirdPage.putExtra("sprite", sprite);
                    toThirdPage.putExtra("playerName", name.getText().toString());
                    toThirdPage.putExtra("difficulty", String.valueOf(difficultyLevel));
                    Toast.makeText(Screen2.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(toThirdPage);
                }
            }
        });
    }
}