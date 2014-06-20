package com.daviancorp.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
    	return new CrimeFragment();
    }

}
