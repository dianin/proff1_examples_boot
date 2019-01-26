package ua.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.demo.test.dao.DataRepository;
import ua.demo.test.domain.Data;

import java.util.List;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    @Transactional
    public Data save(Data data){
        return dataRepository.save(data);
    }

    @Transactional(readOnly = true)
    public List<Data> getAll(){
        return (List<Data>) dataRepository.findAll();
    }

}
