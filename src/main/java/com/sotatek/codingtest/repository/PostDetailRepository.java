package com.sotatek.codingtest.repository;

import com.sotatek.codingtest.core.config.Properties;
import com.sotatek.codingtest.domain.entity.PostDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostDetailRepository extends JpaRepository<PostDetail, Long> {

    Properties properties = new Properties();

    List<PostDetail> findByPostcodesInOrderBySuburbNames(List<String> postcodes);

    @Transactional(readOnly = true)
    @Query(value = "CALL getPost()", nativeQuery = true)
    List<PostDetail> getAll();

    @Query(value = "CALL getPostById(:id)", nativeQuery = true)
    PostDetail getOne(Long id);

}
