package uz.pdp.apphotel.dto;

import lombok.Data;

@Data
public class RoomDto {
    private String number;

    private Integer floor;

    private Double size;

    private Integer hotelId;
}

