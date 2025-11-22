package com.example.mechanicservice.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AvailabilityDTO {

    private boolean available;

    public AvailabilityDTO() {}

    public AvailabilityDTO(boolean available) {
        this.available = available;
    }

}
