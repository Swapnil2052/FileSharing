package com.personal.FileSharing.repository;

import com.personal.FileSharing.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files,Integer> {
}
