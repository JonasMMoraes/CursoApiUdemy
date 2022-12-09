package com.example.cursoapiudemy.service;


import com.example.cursoapiudemy.controller.BookController;
import com.example.cursoapiudemy.data.vo.v1.BookVO;
import com.example.cursoapiudemy.exception.RequiredObjectIsNullException;
import com.example.cursoapiudemy.exception.ResourceNotFoundException;
import com.example.cursoapiudemy.mapper.DozerMapper;
import com.example.cursoapiudemy.model.Book;
import com.example.cursoapiudemy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class BookServices {

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    public BookVO findById(long id) {
        logger.info("Finding one book -> " + id);

        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());

        return vo;
    }

    public List<BookVO> findAll() {
        logger.info("Finding all books");

        List<BookVO> bookVOList = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
        bookVOList.forEach(x -> x.add(linkTo(methodOn(BookController.class).findById(x.getKey())).withSelfRel()));

        return bookVOList;
    }

    public BookVO create(BookVO bookvo) {

        if (bookvo == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Creating one book");
        Book entity = DozerMapper.parseObject(bookvo, Book.class);
        BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO bookvo) {

        if (bookvo == null) {
            throw new RequiredObjectIsNullException();
        }

        logger.info("Updating one book");

        Book entity = repository.findById(bookvo.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setAuthor(bookvo.getAuthor());
        entity.setPrice(bookvo.getPrice());
        entity.setTitle(bookvo.getTitle());
        entity.setlaunchDate(bookvo.getLaunchDate());

        BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one book");
        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}