package io.github.glynch.owcs.rest.client.api;

public record V1RestError(int httpStatus, String problemInstance, String detail, String title, String problemType) {

}
