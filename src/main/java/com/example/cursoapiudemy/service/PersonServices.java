package com.example.cursoapiudemy.service;

import com.example.cursoapiudemy.controller.PersonController;
import com.example.cursoapiudemy.data.vo.v1.PersonVO;
import com.example.cursoapiudemy.exception.RequiredObjectIsNullException;
import com.example.cursoapiudemy.exception.ResourceNotFoundException;
import com.example.cursoapiudemy.mapper.DozerMapper;
import com.example.cursoapiudemy.mapper.custom.PersonMapper;
import com.example.cursoapiudemy.model.Person;
import com.example.cursoapiudemy.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public PersonVO findById(Long id) {
        logger.info("Finding one person -> " + id);
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all person");
        List<PersonVO> personVOList = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        personVOList.forEach(x -> x.add(linkTo(methodOn(PersonController.class).findById(x.getKey())).withSelfRel()));

        return personVOList;
    }

    public PersonVO create(PersonVO personvo) {

        if (personvo == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating one person");
        Person entity = DozerMapper.parseObject(personvo, Person.class);
        PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) {

        if (person == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating one person");

        Person entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one person");
        Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
