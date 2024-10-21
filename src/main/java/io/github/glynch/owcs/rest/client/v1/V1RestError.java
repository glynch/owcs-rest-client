package io.github.glynch.owcs.rest.client.v1;

public record V1RestError(int httpStatus, String problemInstance, String detail, String title, String problemType) {

}
