
package com.service.pokemon1gen.helperClasses.pokemonDataOne;

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
    "genus",
    "language"
})
@Generated("jsonschema2pojo")
public class Genera implements Serializable
{

    @JsonProperty("genus")
    private String genus;
    @JsonProperty("language")
    private Language__1 language;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6072948154267339273L;

    @JsonProperty("genus")
    public String getGenus() {
        return genus;
    }

    @JsonProperty("genus")
    public void setGenus(String genus) {
        this.genus = genus;
    }

    @JsonProperty("language")
    public Language__1 getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(Language__1 language) {
        this.language = language;
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
