package com.sharewire.googlemapsclustering.sample;


import androidx.annotation.NonNull;

import java.util.Random;

import me.tatiyanupanwong.supasin.android.libraries.kits.maps.MapKit;
import me.tatiyanupanwong.supasin.android.libraries.kits.maps.model.LatLng;
import me.tatiyanupanwong.supasin.android.libraries.kits.maps.model.LatLngBounds;

final class RandomLocationGenerator {

    private static final Random RANDOM = new Random();

    @NonNull
    static LatLng generate(@NonNull LatLngBounds bounds) {
        double minLatitude = bounds.getSouthwest().getLatitude();
        double maxLatitude = bounds.getNortheast().getLatitude();
        double minLongitude = bounds.getSouthwest().getLongitude();
        double maxLongitude = bounds.getNortheast().getLongitude();
        return MapKit.newLatLng(
                minLatitude + (maxLatitude - minLatitude) * RANDOM.nextDouble(),
                minLongitude + (maxLongitude - minLongitude) * RANDOM.nextDouble());
    }

    private RandomLocationGenerator() {
    }
}
