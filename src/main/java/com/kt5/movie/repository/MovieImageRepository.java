package com.kt5.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt5.movie.model.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long>{

}
