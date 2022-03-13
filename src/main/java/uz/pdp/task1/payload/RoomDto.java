package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private Integer number;
    private Short floor;
    private Double size;
    private Integer hotelId;
}
