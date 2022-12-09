package com.example.cursoapiudemy.mapper.custom;

import com.example.cursoapiudemy.data.vo.v1.PersonVO;
import com.example.cursoapiudemy.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {

    public Person convertVoToEntity(PersonVO vo) {
        Person person = new Person();
        person.setId(person.getId());
        person.setAddress(person.getAddress());
        person.setGender(person.getGender());
        person.setFirstName(person.getFirstName());
        person.setLastName(person.getLastName());
//        person.setBirthDay(new Date());

        return person;
    }


}
