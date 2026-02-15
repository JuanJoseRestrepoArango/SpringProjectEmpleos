package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Emp;

public interface EmpleadoRepository extends JpaRepository<Emp, Integer> {

    @Query(" SELECT e FROM Emp e WHERE (:nombre IS NULL OR LOWER(e.ename) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND (:deptno IS NULL OR e.deptno.deptno = :deptno)")
    List<Emp> buscarPorNombreYDepartamento(
        @Param("nombre") String nombre,
        @Param("deptno") Integer deptno
    );
}
