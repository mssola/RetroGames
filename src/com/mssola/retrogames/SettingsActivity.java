/*
 * Copyright 2012 Miquel Sabaté <mikisabate@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Library General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.mssola.retrogames;

import com.mssola.helpers.Settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


/**
 * The Activity for the Settings tab.
 */
public class SettingsActivity extends Activity
implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener, Spinner.OnItemSelectedListener
{
    /**
     * On create setup all the listeners.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        RadioGroup level = (RadioGroup) findViewById(R.id.radioGroup1);
        level.setOnCheckedChangeListener(this);

        CheckBox attacked = (CheckBox) findViewById(R.id.checkBox1);
        attacked.setOnCheckedChangeListener(this);

        CheckBox invader = (CheckBox) findViewById(R.id.checkBox2);
        invader.setOnCheckedChangeListener(this);

        CheckBox sudden = (CheckBox) findViewById(R.id.checkBox3);
        sudden.setOnCheckedChangeListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.number_balls, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    /**
     * onCheckedChanged event for the RadioGroup item. It sets the level of
     * difficulty of the game.
     */
    public void onCheckedChanged(RadioGroup group, int id)
    {
        RetroGamesApplication app = (RetroGamesApplication) getApplicationContext();
        Settings s = app.getSettings();
        switch (id) {
        case R.id.radio0:
            s.setLevel(1);
            break;
        case R.id.radio1:
            s.setLevel(2);
            break;
        case R.id.radio2:
            s.setLevel(3);
            break;
        }
    }

    /**
     * onCheckedChanged event for the CompoundButton item. It sets some attributes.
     */
    public void onCheckedChanged(CompoundButton bttn, boolean isChecked)
    {
        RetroGamesApplication app = (RetroGamesApplication) getApplicationContext();
        Settings s = app.getSettings();
        switch (bttn.getId()) {
        case R.id.checkBox1:
            s.setAttacked(isChecked);
            break;
        case R.id.checkBox2:
            s.setInvader(isChecked);
            break;
        case R.id.checkBox3:
            s.setSudden(isChecked);
            break;
        }
    }

    /**
     * onItemSelected event for the Spinner item. It sets how many balls to be used.
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        RetroGamesApplication app = (RetroGamesApplication) getApplicationContext();
        Settings s = app.getSettings();
        s.setBalls(pos + 1);
    }

    /**
     * We're forced to implement this method, do nothing.
     */
    public void onNothingSelected(AdapterView<?> parent)
    {
        /* There's nothing to do here. */
    }
}
