package org.example.parking;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EstacionamientoTest {

    @Test
    public void testRetirarVehiculo() throws Exception {
        //DONE test
        Estacionamiento estacionamiento = new Estacionamiento();

        Vehiculo vehiculo = new Vehiculo("AG333BE", "Peugeot 208", Vehiculo.Tipo.AUTO);
        String dni = "42424242";
        String nombre = "Tomás";

        boolean ingresarVehiculo = estacionamiento.ingresarVehiculo(dni, nombre, vehiculo);
        assertTrue(ingresarVehiculo);

        Ticket ticket = estacionamiento.retirarVehiculo(vehiculo.getPatente());
        assertNotNull("El ticket no debe ser nulo", ticket);
        assertNotNull("La hora de salidad ebe estar marcada: " + ticket.getHoraSalida());

        List<Ticket> vehiculos = estacionamiento.listarVehiculosEstacionados();
        boolean estaEstacionado = vehiculos.stream()
                .anyMatch(t -> t.getVehiculo().getPatente().equals(vehiculo.getPatente()));

        assertFalse("El vehículo no debe estar estacionado", estaEstacionado);
    }

    @Test
    public void testCalcularPrecio() throws Exception {
        // TODO test
        Cliente cliente = new Cliente("113912", "Tomás Breppe");

        Vehiculo auto = new Vehiculo("AG333BE", "Peugeot 208", Vehiculo.Tipo.AUTO);
        Ticket ticket = new Ticket(cliente, auto);

        ticket.setHoraSalida(ticket.getHoraEntrada().plusMinutes(45));
        double precioAuto = ticket.calcularPrecio();
        assertEquals(100, precioAuto, 0.01);

        Vehiculo suv = new Vehiculo("AF546GF", "Peugeot 2008", Vehiculo.Tipo.SUV);
        Ticket ticket2 = new Ticket(cliente, suv);

        ticket2.setHoraSalida(ticket2.getHoraEntrada().plusMinutes(80));
        double precioSuv = ticket2.calcularPrecio();
        assertEquals(130 * 2, precioSuv, 0.01);

        Vehiculo pickUp = new Vehiculo("AE978TY", "BMW X6", Vehiculo.Tipo.PICKUP);
        Ticket ticket3 = new Ticket(cliente, pickUp);

        ticket3.setHoraSalida(ticket3.getHoraEntrada().plusMinutes(130));
        double precioPickUp = ticket3.calcularPrecio();
        assertEquals(180 * 3, precioPickUp, 0.01);
    }

}