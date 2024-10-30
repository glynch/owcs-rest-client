package io.github.glynch.owcs.rest.utils;

import java.util.regex.Pattern;

import com.fatwire.assetapi.data.AssetId;
import com.openmarket.xcelerate.asset.AssetIdImpl;

public abstract class AssetIds {

    private static Pattern ASSETID_PATTERN = Pattern.compile("[A-Za-z_]+:\\d+");

    private AssetIds() {
    }

    public static AssetId of(String assetId) {
        if (ASSETID_PATTERN.matcher(assetId).matches()) {
            String[] parts = assetId.split(":");
            return new AssetIdImpl(parts[0], Long.parseLong(parts[1]));
        }
        throw new IllegalArgumentException("Invalid assetId: " + assetId);
    }

}
