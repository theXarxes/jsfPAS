package jsf.pas.jsfpas.Model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Setter
@NoArgsConstructor
@Getter
public class ResourceDTO {
    UUID id;
    boolean isAllocated = false;
    @NotNull
    int valueOfResource;

}
