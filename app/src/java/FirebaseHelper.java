package com.example.signalstrengthmapping;

import android.location.Location;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {
    public static void uploadData(Location location, int signalStrength) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("signal_logs");
        Map<String, Object> data = new HashMap<>();
        data.put("latitude", location.getLatitude());
        data.put("longitude", location.getLongitude());
        data.put("signal_dbm", signalStrength);
        data.put("timestamp", System.currentTimeMillis());
        dbRef.push().setValue(data);
    }
}
