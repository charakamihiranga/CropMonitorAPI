package lk.ijse.springboot.cropmonitorapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffCountDto {
    private long managers;
    private long administrative;
    private long scientists;
}
