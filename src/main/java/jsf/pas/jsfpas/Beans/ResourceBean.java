package jsf.pas.jsfpas.Beans;

import jsf.pas.jsfpas.Clients.ResourceClient;
import jsf.pas.jsfpas.Model.DTO.ResourceDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Named(value = "resourceBean")
@RequestScoped
@Getter
@Setter
@NoArgsConstructor
public class ResourceBean {

    List<ResourceDTO> mainTable;

    @Inject
    ResourceClient resourceClient;

    @PostConstruct
    private void init(){
        getResources();
    }

    private String findID;

    private String updateID;
    private String updateValue;

    private String addValue;

    public void getResources(){
        mainTable = resourceClient.getResources().readEntity(new GenericType<List<ResourceDTO>>() {});
    }

    public void getResource(){
        Response r = resourceClient.getResource(findID);
        if(r.getStatus() == 404){
            mainTable = new ArrayList();
            return;
        }
        mainTable = new ArrayList(Arrays.asList(r.readEntity(ResourceDTO.class)));
    }

    public void deleteResource(){
        Response r = resourceClient.deleteResource(findID);
        if (r != null){
            if(r.getStatus() == 404){
                return;
            }
        }

    }

    public void updateResource(){
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(UUID.fromString(updateID));
        resourceDTO.setValueOfResource(Integer.parseInt(updateValue));
        Response r = resourceClient.updateResource(resourceDTO);
        if (r != null){
            if (r.getStatus() == 404){
                return;
            }
        }

    }

    public void addResource(){
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setValueOfResource(Integer.parseInt(addValue));
        Response r = resourceClient.createResource(resourceDTO);
        if (r != null){
            if (r.getStatus() == 400){
                return;
            }
        }

    }

}
