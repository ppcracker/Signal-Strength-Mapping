package com.example.signalstrengthmapping;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.location.*;

public class LocationService {
    public static void startLocationUpdates(Context context) {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(context);
        LocationRequest request = LocationRequest.create();
        request.setInterval(10000);
        request.setPriority(Priority.PRIORITY_HIGH_ACCURACY);

        client.requestLocationUpdates(request, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for (Location location : locationResult.getLocations()) {
                    SignalStrengthListener.updateLocation(location);
                }
            }
        }, context.getMainLooper());
    }
}
