package com.daviancorp.android.hellomoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HelloMoonFragment extends Fragment {
	
	private AudioPlayer mPlayer = new AudioPlayer();
	private Button mPlayButton;
	private Button mStopButton;
	private Button mPauseButton;
	
	private void revertResumeText() {
		if (mPauseButton.getText().equals("Resume")) {
			mPauseButton.setText("Pause");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_hello_moon, parent, false);
		
		mPlayButton = (Button)v.findViewById(R.id.hellomoon_playButton);
		mPlayButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mPlayer.play(getActivity());
				revertResumeText();
			}
		});
		
		mStopButton = (Button)v.findViewById(R.id.hellomoon_stopButton);
		mStopButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mPlayer.stop();
				revertResumeText();
			}
		});
		
		mPauseButton = (Button)v.findViewById(R.id.hellomoon_pauseButton);
		mPauseButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Pause
				if ((mPauseButton.getText().equals("Pause")) && 
						(mPlayer.hasPlayed())) {
					mPlayer.pause();
					mPauseButton.setText("Resume");
				}
				// Resume
				else if (mPauseButton.getText().equals("Resume")) {
					mPlayer.start();
					mPauseButton.setText("Pause");
				}
				
			}
		});
		
		return v;
	}

	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mPlayer.stop();
	}
	
}
