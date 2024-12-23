package io.github.glynch.owcs.rest.client.bean.utils;

import java.util.Objects;
import java.util.regex.Pattern;

import com.fatwire.assetapi.data.AssetId;
import com.openmarket.xcelerate.asset.AssetIdImpl;

import io.github.glynch.owcs.rest.client.types.Types;

public abstract class AssetIds {

    private static Pattern ASSETID_PATTERN = Pattern.compile("[A-Za-z_]+:\\d+");

    private AssetIds() {
    }

    public static AssetId of(String value) {
        Objects.requireNonNull(value, "value cannot be null");
        if (ASSETID_PATTERN.matcher(value).matches()) {
            String[] parts = value.split(":");
            return of(parts[0], Long.parseLong(parts[1]));
        }
        throw new IllegalArgumentException("Invalid assetId: " + value);
    }

    public static AssetId of(String type, long id) {
        Objects.requireNonNull(type, "type cannot be null");
        return new AssetIdImpl(type, id);
    }

    public static AssetId of(Types type, long id) {
        Objects.requireNonNull(type, "type cannot be null");
        return of(type.getName(), id);
    }

}
