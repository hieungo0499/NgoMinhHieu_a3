package com.hanu.jsd.a3.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hanu.jsd.a3.entity.FileEntity;


public interface FileDao extends JpaRepository<FileEntity, Long> {
	@Query("SELECT f FROM FileEntity f WHERE f.status IS TRUE")
	Page<FileEntity> getAvailableFile(Pageable pageable);
	@Query("SELECT COUNT(id) FROM FileEntity f WHERE f.name =:name ")
	int countNumberOfVersion(@Param("name") String name);


}
