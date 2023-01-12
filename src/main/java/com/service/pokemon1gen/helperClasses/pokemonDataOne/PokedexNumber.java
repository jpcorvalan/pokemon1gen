
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
    "entry_number",
    "pokedex"
})
@Generated("jsonschema2pojo")
public class PokedexNumber implements Serializable
{

    @JsonProperty("entry_number")
    private Integer entryNumber;
    @JsonProperty("pokedex")
    private Pokedex pokedex;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1293518237969504707L;

    @JsonProperty("entry_number")
    public Integer getEntryNumber() {
        return entryNumber;
    }

    @JsonProperty("entry_number")
    public void setEntryNumber(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    @JsonProperty("pokedex")
    public Pokedex getPokedex() {
        return pokedex;
    }

    @JsonProperty("pokedex")
    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
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
