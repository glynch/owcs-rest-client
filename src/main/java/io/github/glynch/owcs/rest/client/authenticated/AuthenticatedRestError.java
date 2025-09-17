package io.github.glynch.owcs.rest.client.authenticated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticatedRestError {

        private final int errorCode;
        private final String message;

        public AuthenticatedRestError(@JsonProperty("errorCode") int errorCode,
                        @JsonProperty("message") String message) {
                this.errorCode = errorCode;
                this.message = message;
        }

        public int getErrorCode() {
                return errorCode;
        }

        public String getMessage() {
                return message;
        }

}
