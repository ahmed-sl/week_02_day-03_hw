package com.example.springday03.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Park {

    @NotNull (message = "Cannot be null")
    @Size(min = 2,message = "Length more than 2")
    private String rideID;

    @NotEmpty (message = "Cannot be null")
    @Size(min = 4,message = "Length more than 4")
    private String rideName;

    @NotEmpty (message = "Cannot be null")
    @Pattern(regexp = "(?i)(Rollercoaster|thriller|water)",
            message = "can only have these values (Rollercoaster, thriller, water)")
    private String rideType;

    @NotNull (message = "Cannot be null")
  //  @Pattern(regexp = "[0-9]+",message = "must be a number")
    @Positive
    private int tickets;

    @NotNull (message = "Cannot be null")
    @Positive
    private int price;
}
