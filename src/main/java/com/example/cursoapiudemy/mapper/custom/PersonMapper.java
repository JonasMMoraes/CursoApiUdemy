package com.example.cursoapiudemy.mapper.custom;

import com.example.cursoapiudemy.data.vo.v1.PersonVO;
import com.example.cursoapiudemy.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {

    public Person convertVoToEntity(PersonVO vo) {
        Person person = new Person();
        person.setId(vo.getKey());
        person.setAddress(vo.getAddress());
        person.setGender(vo.getGender());
        person.setFirstName(vo.getFirstName());
        person.setLastName(vo.getLastName());
//        person.setBirthDay(new Date());

        return person;
    }


}
