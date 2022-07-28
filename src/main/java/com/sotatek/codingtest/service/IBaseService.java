package com.sotatek.codingtest.service;


import java.util.List;

public interface IBaseService<T> {
    List<T> findAll() throws Exception;

    T save(T t) throws Exception;
}
