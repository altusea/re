package org.example.playground;

import org.example.util.Convertors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car);

    @Mapping(source = "seatCount", target = "numberOfSeats")
    Car carDtoToCar(CarDto carDto);

    default List<CarDto> toCarDtoList(List<Car> carList) {
        return Convertors.mapAsList(carList, this::carToCarDto);
    }

    default List<Car> toCarList(List<CarDto> carDtoList) {
        return Convertors.mapAsList(carDtoList, this::carDtoToCar);
    }
}
