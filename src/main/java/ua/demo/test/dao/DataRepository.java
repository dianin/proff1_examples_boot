package ua.demo.test.dao;

import org.springframework.data.repository.CrudRepository;
import ua.demo.test.domain.Data;

public interface DataRepository extends CrudRepository<Data, Long> {



}
