package jsf.pas.jsfpas.Clients;

import jsf.pas.jsfpas.Model.DTO.UserDTO;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
public class UserClient {

    ClientBuilder clientBuilder = ClientBuilder.newBuilder();
    Client client = clientBuilder.build();
    WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api/user");

    public UserDTO getUser(String login){
        Response r = target.path("/" + login).request(MediaType.APPLICATION_JSON).get();
        if(r.getStatus() == 404){
            return null;
        }
        return r.readEntity(UserDTO.class);
    }

    public List<UserDTO> getByPartLogin(String login){
        return target.path("/part/" + login).request(MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<UserDTO>>() {});
    }

    public List<UserDTO> getUsers(){
        return target.request(MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<UserDTO>>() {});
    }

    public void addUser(UserDTO user){
        target.request().post(Entity.json(user));
    }

    public void updateUser(UserDTO user){
        target.request().put(Entity.json(user));
    }

    public void changeUserActivity(String login){
        target.path("/changeActivity/" + login).request().get();
    }

}
