package MatteoOrlando.CapStone.exceptions;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime dateTimeStamp) {
}