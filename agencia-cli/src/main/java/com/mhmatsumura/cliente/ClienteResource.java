package com.mhmatsumura.cliente;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/cliente-cli")
public class ClienteResource {

    @Inject
    @RestClient
    ClienteService clienteService;
    
    @GET
    @Path("newCliente")
    public String newCliente(){
        Cliente cliente = Cliente.of(99,"Remoto");
        return clienteService.newCliente(cliente);
    }
}
