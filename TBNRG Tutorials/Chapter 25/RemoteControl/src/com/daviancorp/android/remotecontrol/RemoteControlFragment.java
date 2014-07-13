package com.daviancorp.android.remotecontrol;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.daviancorp.framework.Audio;
import com.daviancorp.framework.Sound;
import com.daviancorp.framework.implementation.AndroidAudio;

public class RemoteControlFragment extends Fragment{
	private TextView mSelectedTextView;
	private TextView mWorkingTextView;
	
	private Audio audio;
	private Map<String, Sound> sounds;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {

		audio = new AndroidAudio(getActivity());
		sounds = createSounds();
		
		View v = inflater.inflate(R.layout.fragment_remote_control, parent, false);
		
		mSelectedTextView = (TextView) v
				.findViewById(R.id.fragment_remote_control_selectedTextView);
		mWorkingTextView = (TextView) v
				.findViewById(R.id.fragment_remote_control_workingTextView);
		
		View.OnClickListener numberButtonListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView textView = (TextView) v;
				String working = mWorkingTextView.getText().toString();
				String text = textView.getText().toString();
				if (working.equals("0")) {
					mWorkingTextView.setText(text);
				} else {
					mWorkingTextView.setText(working + text);
				}
				
				sounds.get(text).play(0.75f);
			}
		};
		
		TableLayout tableLayout = (TableLayout) v
				.findViewById(R.id.fragment_remote_control_tableLayout);
		int number = 1;
		for (int i = 2; i < tableLayout.getChildCount() - 1; i++) {
			TableRow row = (TableRow) tableLayout.getChildAt(i);
			for (int j = 0; j < row.getChildCount(); j++) {
				Button button = (Button) row.getChildAt(j);
				button.setText("" + number);
				button.setOnClickListener(numberButtonListener);
				number++;
			}
		}
		
		TableRow bottomRow = (TableRow) tableLayout
				.getChildAt(tableLayout.getChildCount() - 1);
		
		Button deleteButton = (Button) bottomRow.getChildAt(0);
		deleteButton.setText("Delete");
		deleteButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mWorkingTextView.setText("0");
				sounds.get("Delete").play(0.75f);
			}
		});
		
		Button zeroButton = (Button) bottomRow.getChildAt(1);
		zeroButton.setText("0");
		zeroButton.setOnClickListener(numberButtonListener);

		Button enterButton = (Button) bottomRow.getChildAt(2);
		enterButton.setText("Enter");
		enterButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CharSequence working = mWorkingTextView.getText();
				if (working.length() > 0)
					mSelectedTextView.setText(working);
				mWorkingTextView.setText("0");
				sounds.get("Enter").play(0.75f);
			}
		});
		
		return v;
	}
	
	private Map<String, Sound> createSounds() {
		Map<String, Sound> sounds = new HashMap<>();
		
		sounds.put("0", audio.createSound("audiocheck.net_dtmf_0.wav"));
		sounds.put("1", audio.createSound("audiocheck.net_dtmf_1.wav"));
		sounds.put("2", audio.createSound("audiocheck.net_dtmf_2.wav"));
		sounds.put("3", audio.createSound("audiocheck.net_dtmf_3.wav"));
		sounds.put("4", audio.createSound("audiocheck.net_dtmf_4.wav"));
		sounds.put("5", audio.createSound("audiocheck.net_dtmf_5.wav"));
		sounds.put("6", audio.createSound("audiocheck.net_dtmf_6.wav"));
		sounds.put("7", audio.createSound("audiocheck.net_dtmf_7.wav"));
		sounds.put("8", audio.createSound("audiocheck.net_dtmf_8.wav"));
		sounds.put("9", audio.createSound("audiocheck.net_dtmf_9.wav"));
		sounds.put("Enter", audio.createSound("audiocheck.net_dtmf_#.wav"));
		sounds.put("Delete", audio.createSound("audiocheck.net_dtmf_-.wav"));
		
		return sounds;
	}
}
