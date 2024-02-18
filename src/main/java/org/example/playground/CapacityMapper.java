package org.example.playground;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CapacityMapper {

    CapacityMapper INSTANCE = Mappers.getMapper(CapacityMapper.class);

    Bucket toBucket(Bottle bottle);

    Bottle toBottle(Bucket bucket);
}
