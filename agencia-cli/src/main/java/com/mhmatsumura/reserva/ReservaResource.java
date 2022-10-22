package com.mhmatsumura.reserva;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@Path("/reserva-cli")
public class ReservaResource {

    @Inject
    @RestClient
    ReservaService reservaService;

    @GET
    @Path("newReserva")
    public String newReserva(){
        Reserva reserva = Reserva.of(0,2);
        return reservaService.newReserva(reserva);
    }
    
}
