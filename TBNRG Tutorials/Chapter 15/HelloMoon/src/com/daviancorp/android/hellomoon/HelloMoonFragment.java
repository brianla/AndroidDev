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
	
	private String mPauseResume;
	
	private void revertResumeText() {
		if (mPauseButton.getText().equals(getResources().getString(R.string.hellomoon_resume))) {
			setPauseButtonText(getResources().getString(R.string.hellomoon_pause));
		}
	}
	
	private void setPauseButtonText(String text) {
		if (text == null) text = getResources().getString(R.string.hellomoon_pause);
		mPauseResume = text;
		mPauseButton.setText(mPauseResume);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
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
		setPauseButtonText(mPauseResume);
		mPauseButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Pause
				if ((mPauseButton.getText().equals(getResources().getString(
						R.string.hellomoon_pause))) && 
						(mPlayer.hasPlayed())) {
					mPlayer.pause();
					setPauseButtonText(getResources().getString(R.string.hellomoon_resume));
				}
				// Resume
				else if (mPauseButton.getText().equals(getResources().getString(R.string.hellomoon_resume))) {
					mPlayer.start();
					setPauseButtonText(getResources().getString(R.string.hellomoon_pause));
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
