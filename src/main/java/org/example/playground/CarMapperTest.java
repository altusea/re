package org.example.playground;

public class CarMapperTest {

    public static void main(String[] args) {

        CarDto carDto = new CarDto();
        carDto.setMake("Porsche");
        carDto.setSeatCount(2);
        carDto.setType("SUPER");
        System.out.println(carDto);

        Car car = CarMapper.INSTANCE.carDtoToCar(carDto);
        System.out.println(car);
    }
}
