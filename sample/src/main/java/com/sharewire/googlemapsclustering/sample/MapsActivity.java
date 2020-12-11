package com.sharewire.googlemapsclustering.sample;

import android.os.Bundle;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;


import net.sharewire.googlemapsclustering.Cluster;
import net.sharewire.googlemapsclustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

import me.tatiyanupanwong.supasin.android.libraries.kits.maps.MapFragment;
import me.tatiyanupanwong.supasin.android.libraries.kits.maps.MapKit;
import me.tatiyanupanwong.supasin.android.libraries.kits.maps.model.LatLngBounds;
import me.tatiyanupanwong.supasin.android.libraries.kits.maps.model.MapClient;

public class MapsActivity extends FragmentActivity implements MapKit.OnMapReadyCallback {

    private static final String TAG = MapsActivity.class.getSimpleName();

    private static final LatLngBounds NETHERLANDS = MapKit.newLatLngBounds(
            MapKit.newLatLng(50.77083, 3.57361), MapKit.newLatLng(53.35917, 7.10833));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if (savedInstanceState == null) {
            setupMapFragment();
        }
    }

    @Override
    public void onMapReady(final MapClient mapClient) {
        mapClient.setOnMapLoadedCallback(new MapClient.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mapClient.animateCamera(MapKit.getCameraUpdateFactory().newLatLngBounds(NETHERLANDS, 0));
            }
        });

        ClusterManager<SampleClusterItem> clusterManager = new ClusterManager<>(this, mapClient);
        clusterManager.setCallbacks(new ClusterManager.Callbacks<SampleClusterItem>() {
            @Override
            public boolean onClusterClick(@NonNull Cluster<SampleClusterItem> cluster) {
                Log.d(TAG, "onClusterClick");
                return false;
            }

            @Override
            public boolean onClusterItemClick(@NonNull SampleClusterItem clusterItem) {
                Log.d(TAG, "onClusterItemClick");
                return false;
            }
        });
        mapClient.setOnCameraIdleListener(clusterManager);

        List<SampleClusterItem> clusterItems = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            clusterItems.add(new SampleClusterItem(
                    RandomLocationGenerator.generate(NETHERLANDS)));
        }
        clusterManager.setItems(clusterItems);
    }

    private void setupMapFragment() {
        MapFragment mapFragment = (MapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.setRetainInstance(true);
        mapFragment.getMapAsync(this);
    }
}
