package com.example.appjam.fb;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class FbUser {
    @JsonProperty("Lon")
    private String Lon;

    @JsonProperty("Token")
    private String Token;

    @JsonProperty("Lat")
    private String Lat;
}