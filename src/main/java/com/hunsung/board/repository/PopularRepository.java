package com.hunsung.board.repository;

import com.hunsung.board.entity.PopularSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopularRepository extends JpaRepository<PopularSearchEntity, String> {

    public List<PopularSearchEntity> findTop10ByOrderByPopularSearchCountDesc();

}
