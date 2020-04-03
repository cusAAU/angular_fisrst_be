package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.OwnerDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.impl.OwnerJdbcDao;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {

    public OwnerDto entityToDto(Owner owner) {
        return new OwnerDto(owner.getId(), owner.getName(), owner.getCreatedAt(), owner.getUpdatedAt());
    }

    //todo
//    public boolean entityToDao(Owner owner){
//
//    }

}
