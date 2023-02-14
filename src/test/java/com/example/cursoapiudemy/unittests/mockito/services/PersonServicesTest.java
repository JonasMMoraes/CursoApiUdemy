package com.example.cursoapiudemy.unittests.mockito.services;

import com.example.cursoapiudemy.data.vo.v1.PersonVO;
import com.example.cursoapiudemy.exception.RequiredObjectIsNullException;
import com.example.cursoapiudemy.model.Person;
import com.example.cursoapiudemy.repository.PersonRepository;
import com.example.cursoapiudemy.services.PersonServices;
import com.example.cursoapiudemy.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository personRepository;


    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        person.setId(1L);
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        PersonVO result = service.findById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getKey());
        Assertions.assertNotNull(result.getLinks());

        Assertions.assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        Assertions.assertEquals("Addres Test1", result.getAddress());
        Assertions.assertEquals("First Name Test1", result.getFirstName());
        Assertions.assertEquals("Last Name Test1", result.getLastName());
        Assertions.assertEquals("Female", result.getGender());
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();

        Mockito.when(personRepository.findAll()).thenReturn(list);

        List<PersonVO> people = service.findAll();
        Assertions.assertNotNull(people);
        Assertions.assertEquals(14, people.size());

        for (PersonVO person : people) {
            Assertions.assertNotNull(person);
            Assertions.assertNotNull(person.getKey());
            Assertions.assertNotNull(person.getLinks());

            Assertions.assertTrue(person.toString().contains("links: [</person/" + people.indexOf(person) + ">;rel=\"self\"]"));
            Assertions.assertEquals("Addres Test" + people.indexOf(person), person.getAddress());
            Assertions.assertEquals("First Name Test" + people.indexOf(person), person.getFirstName());
            Assertions.assertEquals("Last Name Test" + people.indexOf(person), person.getLastName());
            Assertions.assertEquals(person.getGender(), ((people.indexOf(person) % 2) == 0) ? "Male" : "Female");
        }
    }

    @Test
    void create() {
        Person entity = input.mockEntity(1);
        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        Mockito.when(personRepository.save(entity)).thenReturn(persisted);

        PersonVO result = service.create(vo);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getKey());
        Assertions.assertNotNull(result.getLinks());

        Assertions.assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        Assertions.assertEquals("Addres Test1", result.getAddress());
        Assertions.assertEquals("First Name Test1", result.getFirstName());
        Assertions.assertEquals("Last Name Test1", result.getLastName());
        Assertions.assertEquals("Female", result.getGender());
    }

    @Test
    void createWhithNullPerson() {

        Exception exception = Assertions.assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "It is not allowd to persist a null object!";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {

        Person entity = input.mockEntity(1);
        entity.setId(1L);

        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(personRepository.save(entity)).thenReturn(persisted);

        PersonVO result = service.update(vo);
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getKey());
        Assertions.assertNotNull(result.getLinks());

        Assertions.assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
        Assertions.assertEquals("Addres Test1", result.getAddress());
        Assertions.assertEquals("First Name Test1", result.getFirstName());
        Assertions.assertEquals("Last Name Test1", result.getLastName());
        Assertions.assertEquals("Female", result.getGender());
    }

    @Test
    void updateWhithNullPerson() {

        Exception exception = Assertions.assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "It is not allowd to persist a null object!";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete(Long id) {
        Person entity = input.mockEntity(1);
        entity.setId(1L);
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);
    }
}