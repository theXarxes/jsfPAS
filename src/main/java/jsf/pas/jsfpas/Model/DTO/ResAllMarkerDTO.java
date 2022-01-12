package jsf.pas.jsfpas.Model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ResAllMarkerDTO {
    UUID id;
    LocalDate start;
    UUID resourceId;
    boolean isEnded;
    @NotNull
    String user;
    int delay = 0;
}
