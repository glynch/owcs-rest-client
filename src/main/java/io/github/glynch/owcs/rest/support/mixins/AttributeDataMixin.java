package io.github.glynch.owcs.rest.support.mixins;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatwire.rest.beans.Blob;
import com.fatwire.rest.beans.Struct;
import com.fatwire.rest.beans.Webreference;

public abstract class AttributeDataMixin {

    @JsonProperty("listList")
    public abstract java.util.List<com.fatwire.rest.beans.List> getListLists();

    @JsonProperty("structList")
    public abstract List<Struct> getStructLists();

    @JsonProperty("webreferenceList")
    public abstract List<Webreference> getWebreferenceLists();

    @JsonProperty("blobList")
    public abstract List<Blob> getBlobLists();

    @JsonProperty("booleanList")
    public abstract List<Boolean> getBooleanLists();

    @JsonProperty("doubleList")
    public abstract List<Double> getDoubleLists();

    @JsonProperty("longList")
    public abstract List<Long> getLongLists();

    @JsonProperty("decimalList")
    public abstract java.util.List<BigDecimal> getDecimalLists();

    @JsonProperty("integerList")
    public abstract List<Integer> getIntegerLists();

    @JsonProperty("dateList")

    public abstract java.util.List<Date> getDateLists();

    @JsonProperty("stringList")
    public abstract java.util.List<String> getStringLists();

}
