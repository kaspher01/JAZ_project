package com.hoth.webapi.services.utilis;

import com.hoth.webapi.contract.VehicleDto;
import com.hoth.data.model.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleDtoMapper implements IMapDtos<Vehicle, VehicleDto> {
    @Override
    public VehicleDto map(Vehicle vehicle) {
        return map(vehicle, new VehicleDto());
    }

    @Override
    public VehicleDto map(Vehicle vehicle, VehicleDto vehicleDto) {
        vehicleDto.setName(vehicle.getName());
        vehicleDto.setModel(vehicle.getModel());
        vehicleDto.setVehicleClass(vehicle.getVehicleClass());
        vehicleDto.setManufacturer(vehicle.getManufacturer());
        vehicleDto.setLength(vehicle.getLength());
        vehicleDto.setCostInCredits(vehicle.getCostInCredits());
        vehicleDto.setCrew(vehicle.getCrew());
        vehicleDto.setPassengers(vehicle.getPassengers());
        vehicleDto.setMaxAtmospheringSpeed(vehicle.getMaxAtmospheringSpeed());
        return vehicleDto;
    }
}
