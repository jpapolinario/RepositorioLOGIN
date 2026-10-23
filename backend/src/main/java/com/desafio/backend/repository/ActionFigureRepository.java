package com.desafio.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.backend.model.ActionFigure;

public interface ActionFigureRepository extends JpaRepository<ActionFigure, Integer> {
}