package org.annoml.servant.SpringAnnoMLServant.service;

import org.annoml.servant.SpringAnnoMLServant.dto.AuthorDto;
import org.annoml.servant.SpringAnnoMLServant.exception.UserAlreadyExistsException;
import org.annoml.servant.SpringAnnoMLServant.model.user.Author;
import org.annoml.servant.SpringAnnoMLServant.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public AuthorDto registerNewAccount(AuthorDto authorDto) {
        Author author = convertToEntity(authorDto);
        if (authorRepository.findByUsername(author.getUsername()) != null) {
            throw new UserAlreadyExistsException(authorDto.getUsername());
        }
        author = authorRepository.saveAndFlush(author);
        return convertToDto(author);
    }


    private Author convertToEntity(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }

    private AuthorDto convertToDto(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }
}
