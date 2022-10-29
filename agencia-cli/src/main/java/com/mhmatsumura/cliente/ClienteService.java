package com.mhmatsumura.cliente;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://34.151.228.44/cliente")
public interface ClienteService {
    
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public List<Cliente> get(); 

    @GET
    @Path("findById")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(unit = ChronoUnit.MILLIS,value = 3000)
    @Fallback(fallbackMethod = "fallback")
    @CircuitBreaker(
        requestVolumeThreshold = 4, //quantidade de requisições de referência
        failureRatio = .5, // se 50% de requestVolumeThreshold falhar
        delay = 6000, // aguarda 6 segundos após a falha
        successThreshold = 1// taxa mínima de sucesso para religar o circuito de forma permanente
    )
    public Cliente findById(@QueryParam("id") long id);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String newCliente(Cliente cliente);


    private Cliente fallback(long id){
        return Cliente.of(0,"");
    } 
   

}
