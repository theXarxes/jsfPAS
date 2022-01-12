package jsf.pas.jsfpas.Clients;

import jsf.pas.jsfpas.Model.DTO.ResAllMarkerDTO;
import jsf.pas.jsfpas.Model.DTO.ResourceDTO;

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
public class ResourceClient {

    ClientBuilder clientBuilder = ClientBuilder.newBuilder();
    Client client = clientBuilder.build();
    WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api/resource");

    public Response getResource(String id){
        return target.path("/" + id).request(MediaType.APPLICATION_JSON).get();
    }

    public Response getResources(){
        return target.request(MediaType.APPLICATION_JSON).get();
    }

    public Response createResource(ResourceDTO resource){
        return target.request(MediaType.APPLICATION_JSON).post(Entity.json(resource));
    }

    public Response updateResource(ResourceDTO resource){
        return target.request(MediaType.APPLICATION_JSON).put(Entity.json(resource));
    }

    public Response deleteResource(String id){
        return target.path("/" + id).request().delete();
    }
}
