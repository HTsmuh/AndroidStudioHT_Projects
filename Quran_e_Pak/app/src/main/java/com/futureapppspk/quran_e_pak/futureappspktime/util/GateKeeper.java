package com.futureapppspk.quran_e_pak.futureappspktime.util;

import android.content.pm.PackageManager;

import com.futureapppspk.quran_e_pak.futureappspktime.VARIABLE;


public class GateKeeper {
	public static String getVersionName() {
		String versionName = "undefined";
		try {
			versionName = VARIABLE.context.getPackageManager().getPackageInfo(VARIABLE.context.getPackageName(), PackageManager.GET_META_DATA).versionName;
		} catch(Exception ex) { }
		return versionName;
	}
}