package jsf.pas.jsfpas.Beans;

import jsf.pas.jsfpas.Clients.UserClient;
import jsf.pas.jsfpas.Model.DTO.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named(value = "userBean")
@RequestScoped
@Getter
@Setter
@NoArgsConstructor
public class UserBean {

    @Inject
    UserClient userClient;

    @PostConstruct
    private void init(){
        users = userClient.getUsers();
    }

    private List<UserDTO> users;

    private String findLogin;

    private String addName;
    private String addLogin;
    private String addAccessLevel;

    private String updateName;
    private String updateLogin;

    private String changeLogin;

    public void getAll(){
        users = userClient.getUsers();
    }

    public void getUser(){
        UserDTO u = userClient.getUser(findLogin);
        users = new ArrayList(Arrays.asList(u));
    }

    public void getAllLogin(){
        users =  userClient.getByPartLogin(findLogin);
    }

    public void addUser(){
        userClient.addUser(new UserDTO(addLogin, addName, Integer.parseInt(addAccessLevel)));
    }

    public void updateUser(){
        UserDTO user = new UserDTO();
        user.setName(updateName);
        user.setLogin(updateLogin);
        userClient.updateUser(user);
    }

    public void changeLoginActivity(){
        userClient.changeUserActivity(changeLogin);
    }
}
