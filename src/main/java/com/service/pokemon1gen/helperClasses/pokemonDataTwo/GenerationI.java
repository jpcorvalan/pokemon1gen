
package com.service.pokemon1gen.helperClasses.pokemonDataTwo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "red-blue",
    "yellow"
})
@Generated("jsonschema2pojo")
public class GenerationI implements Serializable
{

    @JsonProperty("red-blue")
    private RedBlue redBlue;
    @JsonProperty("yellow")
    private Yellow yellow;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5576595842246663089L;

    @JsonProperty("red-blue")
    public RedBlue getRedBlue() {
        return redBlue;
    }

    @JsonProperty("red-blue")
    public void setRedBlue(RedBlue redBlue) {
        this.redBlue = redBlue;
    }

    @JsonProperty("yellow")
    public Yellow getYellow() {
        return yellow;
    }

    @JsonProperty("yellow")
    public void setYellow(Yellow yellow) {
        this.yellow = yellow;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
