package com.example.signalstrengthmapping;

import android.content.Context;
import android.location.Location;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

public class SignalStrengthListener extends PhoneStateListener {
    private static Location currentLocation;
    private final Context context;

    public SignalStrengthListener(Context context) {
        this.context = context;
    }

    public static void updateLocation(Location location) {
        currentLocation = location;
    }

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        int strength = signalStrength.getGsmSignalStrength();
        int dbm = -113 + 2 * strength;
        FirebaseHelper.uploadData(currentLocation, dbm);
    }

    public void register() {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        manager.listen(this, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }
}
