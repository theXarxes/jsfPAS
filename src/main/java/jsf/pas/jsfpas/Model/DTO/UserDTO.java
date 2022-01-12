package jsf.pas.jsfpas.Model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    @NotBlank
    String login;
    boolean isActive;
    @NotNull
    int accessLevel = 0;
    @NotBlank
    String name;

    public UserDTO(String login, String name, int accessLevel) {
        this.login = login;
        this.accessLevel = accessLevel;
        this.name = name;
    }

}
