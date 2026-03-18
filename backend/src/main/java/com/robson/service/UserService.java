package com.robson.service;

import com.robson.dto.PagedResponseDto;
import com.robson.dto.UserRequestDto;
import com.robson.dto.UserResponseDto;
import com.robson.entity.User;
import com.robson.exception.ResourceNotFoundException;
import com.robson.mapper.UserMapper;
import com.robson.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public PagedResponseDto<UserResponseDto> findAll(String name, int page, int size) {
        PanacheQuery<User> query;

        if (name != null && !name.isBlank()) {
            query = userRepository.findByName(name);
        } else {
            query = userRepository.findAll();
        }

        long total = query.count();

        List<UserResponseDto> users = query
                .page(Page.of(page, size))
                .list()
                .stream()
                .map(UserMapper::toResponseDto)
                .toList();

        int totalPages = (int) Math.ceil((double) total / size);

        return new PagedResponseDto<>(users, page, size, total, totalPages);
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }

        return UserMapper.toResponseDto(user);
    }

    @Transactional
    public UserResponseDto create(UserRequestDto dto) {
        User user = UserMapper.toEntity(dto);
        userRepository.persist(user);
        return UserMapper.toResponseDto(user);
    }

    @Transactional
    public UserResponseDto update(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }

        UserMapper.updateEntity(user, dto);
        return UserMapper.toResponseDto(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }

        userRepository.delete(user);
    }
}