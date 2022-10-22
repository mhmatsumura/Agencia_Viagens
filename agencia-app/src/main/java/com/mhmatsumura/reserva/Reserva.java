package com.mhmatsumura.reserva;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.mhmatsumura.cliente.Cliente;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Reserva extends PanacheEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    public Cliente cliente;
    
}
