package jsf.pas.jsfpas.Beans;

import jsf.pas.jsfpas.Clients.AllocationClient;
import jsf.pas.jsfpas.Clients.ResourceClient;
import jsf.pas.jsfpas.Model.DTO.ResAllMarkerDTO;
import jsf.pas.jsfpas.Model.DTO.ResourceDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named(value = "allocationBean")
@RequestScoped
@Getter
@Setter
@NoArgsConstructor
public class AllocationBean {

    List<ResAllMarkerDTO> mainTable;
    int listLength;

    String findId;

    String resourceFindId;

    String userFindLogin;

    String allocateResourceId;
    String allocateUserLogin;
    String allocationDelay;

    String endAllocationId;

    String deleteAllocationId;

    @Inject
    AllocationClient allocationClient;

    @PostConstruct
    private void init(){
        getAllocations();
    }

    private void listCount(){
        listLength = mainTable.size();
    }

    public void getAllocations(){
        mainTable = allocationClient.getMarkers().readEntity(new GenericType<List<ResAllMarkerDTO>>() {});
        listCount();
    }

    public void getAllocation(){
        Response r = allocationClient.getMarker(findId);
        if(r.getStatus() == 404){
            mainTable.clear();
            listCount();
            return;
        }
        mainTable = new ArrayList(Arrays.asList(r.readEntity(ResAllMarkerDTO.class)));
        listCount();
    }

    public void getResourceMarkers(){
        Response r = allocationClient.getResourceMarkers(resourceFindId);
        if(r.getStatus() == 404){
            mainTable.clear();
            listCount();
            return;
        }
        mainTable = r.readEntity(new GenericType<List<ResAllMarkerDTO>>() {});
        listCount();
    }

   public void getUserMarkers(){
        Response r = allocationClient.getUserMarkers(userFindLogin);
        if (r.getStatus() == 404){
            mainTable.clear();
            listCount();
            return;
        }
        mainTable = r.readEntity(new GenericType<List<ResAllMarkerDTO>>() {});
        listCount();
   }

   public void getUserCurrentMarkers(){
       Response r = allocationClient.getUserCurrentMarkers(userFindLogin);
       if (r.getStatus() == 404){
           mainTable.clear();
           listCount();
           return;
       }
       mainTable = r.readEntity(new GenericType<List<ResAllMarkerDTO>>() {});
       listCount();
   }

   public void getUserEndedMarkers(){
       Response r = allocationClient.getUserEndedMarkers(userFindLogin);
       if (r.getStatus() == 404){
           mainTable.clear();
           listCount();
           return;
       }
       mainTable = r.readEntity(new GenericType<List<ResAllMarkerDTO>>() {});
       listCount();
   }

    public void allocateResource(){
        ResAllMarkerDTO markerDTO = new ResAllMarkerDTO();
        markerDTO.setUser(userFindLogin);
        markerDTO.setDelay(Integer.parseInt(allocationDelay));
        Response r = allocationClient.allocateResource(allocateResourceId, markerDTO);
        if(r.getStatus() == 400){
            return;
        }
    }

    public void endAllocation(){
        Response r = allocationClient.endAllocation(endAllocationId);
        if(r.getStatus() == 404){
            return;
        }
    }

    public void deleteAllocation(){
        Response r = allocationClient.deleteAllocation(deleteAllocationId);
        if(r.getStatus() == 404){
            return;
        } else if (r.getStatus() == 400){
            return;
        }
    }

}
