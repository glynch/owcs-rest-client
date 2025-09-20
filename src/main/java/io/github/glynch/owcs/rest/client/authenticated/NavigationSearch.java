package io.github.glynch.owcs.rest.client.authenticated;

import com.fatwire.rest.beans.SitePlanNodeCodeEnum;

public class NavigationSearch {

    private final String depth;
    private final SitePlanNodeCodeEnum code;

    public NavigationSearch(String depth, SitePlanNodeCodeEnum code) {
        this.depth = depth;
        this.code = code;
    }

    public String getDepth() {
        return depth;
    }

    public SitePlanNodeCodeEnum getCode() {
        return code;
    }

    public static class Builder {

        private String depth;
        private SitePlanNodeCodeEnum code;

        Builder depth(String depth) {
            this.depth = depth;
            return this;
        }

        public Builder code(SitePlanNodeCodeEnum code) {
            this.code = code;
            return this;
        }

        public NavigationSearch build() {
            return new NavigationSearch(depth, code);
        }
    }

}
