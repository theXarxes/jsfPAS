package jsf.pas.jsfpas.Clients;

import jsf.pas.jsfpas.Model.DTO.ResAllMarkerDTO;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
public class AllocationClient {

    ClientBuilder clientBuilder = ClientBuilder.newBuilder();
    Client client = clientBuilder.build();
    WebTarget target = client.target("http://localhost:8080/RestPAS-1.0-SNAPSHOT/api/allocate");

    public Response getMarker(String id){
        return target.path("/resource/" + id).request(MediaType.APPLICATION_JSON).get();
    }

    public Response getMarkers(){
        return target.request().get();
    }

    public Response getResourceMarkers(String id){
         return target.path("/resource/" + id).request().get();
    }

    public Response getUserMarkers(String id){
        return target.path("/user/" + id).request().get();
    }

    public Response getUserCurrentMarkers(String id){
        return target.path("/user/" + id + "/current").request().get();
    }

    public Response getUserEndedMarkers(String id){
        return target.path("/user/" + id + "/ended").request().get();
    }

    public Response allocateResource(String id, ResAllMarkerDTO marker){
        return target.path("/add/" + id).request(MediaType.APPLICATION_JSON).post(Entity.json(marker));
    }

    public Response endAllocation(String id){
        return target.path("/end/" + id).request().post(Entity.json(null));
    }

    public Response deleteAllocation(String id){
        return target.path("/" + id).request().delete();
    }
}
