package com.daviancorp.android.criminalintent;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

public class TwoOptionsFragment extends DialogFragment {
	public static final String EXTRA_DATE_OPTION =
			"com.daviancorp.android.criminalintent.date_option";
	
	private static final String DIALOG_DATE_OR_TIME = "date_or_time";
	
	private Date mDate;
	
	private void onClickHelper(String option) {
		FragmentManager fm = getActivity()
				.getSupportFragmentManager();
		DialogFragment dialog;
		
		if (option.equals("date")) {
			dialog = DatePickerFragment.newInstance(mDate);
		}
		else{
			dialog = TimePickerFragment.newInstance(mDate);;
		}
		dialog.setTargetFragment(getTargetFragment(), getTargetRequestCode());
		dialog.show(fm, DIALOG_DATE_OR_TIME);
	}
	
	public static TwoOptionsFragment newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE_OPTION, date);
		
		TwoOptionsFragment fragment = new TwoOptionsFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mDate = (Date)getArguments().getSerializable(EXTRA_DATE_OPTION);
		
		return new AlertDialog.Builder(getActivity())
		.setTitle(R.string.date_option_title)
		.setPositiveButton(
				R.string.date_change_time, 
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						onClickHelper("time");
					}
				})
		.setNegativeButton(
				R.string.date_change_date, 
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						onClickHelper("date");
					}
				})		
		.create();
	}
}
