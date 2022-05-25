package com.example.springday03.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@AllArgsConstructor @Data
public class Employees {

    @NotEmpty (message = "ID is required")
    @Size(min = 2,message = "ID must more than 2")
    private String id;

    @NotEmpty (message = "ID is required")
    @Size(min = 2,message = "ID must more than 2")
    private String name;

    @NotNull (message = "age is required")
    @Positive
    @Min(value = 25,message = "you must be older than 25")
    private int age;

    @AssertFalse(message = "onLeave must be false")
    private boolean onLeave;

    @NotEmpty (message = "date is required")
    @Pattern(regexp = "(\\d{4})", message = "invalid date")
    private String employmentYear;

    @NotNull (message = "annualLeave is required")
    private int annualLeave;


}
