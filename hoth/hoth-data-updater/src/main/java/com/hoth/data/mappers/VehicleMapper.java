package com.hoth.data.mappers;

import com.hoth.client.contract.VehicleDto;
import com.hoth.data.model.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper implements IMapEntities<VehicleDto, Vehicle> {
    @Override
    public Vehicle map(VehicleDto vehicleDto) {
        return map(vehicleDto, new Vehicle());
    }

    @Override
    public Vehicle map(VehicleDto vehicleDto, Vehicle vehicle) {
        vehicle.setName(vehicleDto.getName());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setVehicleClass(vehicleDto.getVehicleClass());
        vehicle.setManufacturer(vehicleDto.getManufacturer());
        vehicle.setCostInCredits(vehicleDto.getCostInCredits());
        vehicle.setLength(vehicleDto.getLength());
        vehicle.setCrew(vehicleDto.getCrew());
        vehicle.setPassengers(vehicleDto.getPassengers());
        vehicle.setMaxAtmospheringSpeed(vehicleDto.getMaxAtmospheringSpeed());
        return vehicle;
    }
}
