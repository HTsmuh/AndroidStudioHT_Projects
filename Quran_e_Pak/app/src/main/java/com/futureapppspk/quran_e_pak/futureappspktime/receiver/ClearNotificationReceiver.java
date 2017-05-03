package com.futureapppspk.quran_e_pak.futureappspktime.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.futureapppspk.quran_e_pak.futureappspktime.Notifier;

public class ClearNotificationReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Notifier.stop();
	}
}