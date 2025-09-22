package io.github.glynch.owcs.rest.bean.utils;

import java.util.regex.Pattern;

import org.springframework.util.Assert;

import com.fatwire.assetapi.data.AssetId;
import com.openmarket.xcelerate.asset.AssetIdImpl;

public class AssetIds {

    private static Pattern ASSETID_PATTERN = Pattern.compile("[A-Za-z_]+:\\d+");

    private AssetIds() {
    }

    public static AssetId of(String value) {
        Assert.hasText(value, "value cannot be empty or null");
        if (ASSETID_PATTERN.matcher(value).matches()) {
            String[] parts = value.split(":");
            return of(parts[0], Long.parseLong(parts[1]));
        }
        throw new IllegalArgumentException("Invalid assetId: " + value);
    }

    public static AssetId of(String type, long id) {
        Assert.hasText(type, "type cannot be empty or null");
        return new AssetIdImpl(type, id);
    }

}
