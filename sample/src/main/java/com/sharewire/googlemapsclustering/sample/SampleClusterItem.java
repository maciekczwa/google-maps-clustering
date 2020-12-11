package com.sharewire.googlemapsclustering.sample;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import net.sharewire.googlemapsclustering.ClusterItem;

import me.tatiyanupanwong.supasin.android.libraries.kits.maps.model.LatLng;

class SampleClusterItem implements ClusterItem {

    private final LatLng location;

    SampleClusterItem(@NonNull LatLng location) {
        this.location = location;
    }

    @Override
    public double getLatitude() {
        return location.getLatitude();
    }

    @Override
    public double getLongitude() {
        return location.getLongitude();
    }

    @Nullable
    @Override
    public String getTitle() {
        return null;
    }

    @Nullable
    @Override
    public String getSnippet() {
        return null;
    }
}
