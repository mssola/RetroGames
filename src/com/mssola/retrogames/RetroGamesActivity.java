package com.mssola.retrogames;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


public class RetroGamesActivity extends TabActivity
{
	private TabHost tabHost;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTabs();
    }
    
    private void setTabs()
    {
		tabHost = getTabHost();
		addTab(R.string.games_tab, GamesActivity.class);
		addTab(R.string.stats_tab, StatisticsActivity.class);
		addTab(R.string.hist_tab, HistoryActivity.class);
		addTab(R.string.settings_tab, SettingsActivity.class);
	}
	
	private void addTab(int labelId, Class<?> activity)
	{
		Intent intent = new Intent(this, activity);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);		
		
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(R.drawable.tab_info);
		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}
}