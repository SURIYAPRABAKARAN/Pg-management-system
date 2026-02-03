package com.pg.adminservice.dto;

import com.pg.adminservice.entity.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private Long id;

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotNull(message = "Room type is required")
    private RoomType roomType;

    @NotNull(message = "Sharing type is required")
    private Integer sharingType;

    private Integer floorNumber;

    private Boolean bathroomAttached;

    @NotNull(message = "Room rent is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Room rent must be positive")
    private BigDecimal roomRent;

    @DecimalMin(value = "0.0", inclusive = false, message = "Room advance must be positive")
    private BigDecimal roomAdvance;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
