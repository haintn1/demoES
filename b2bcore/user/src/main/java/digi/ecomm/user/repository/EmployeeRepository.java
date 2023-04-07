package digi.ecomm.user.repository;

import digi.ecomm.entity.user.Employee;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("employeeRepository")
@RepositoryRestResource(path = EmployeeRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EmployeeRepository.ITEM_RESOURCE_REL)
public interface EmployeeRepository extends BaseRepository<Employee, Long> {

    String PATH = "employees";
    String ITEM_RESOURCE_REL = "employee";

}
