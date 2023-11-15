package com.nicezi.patrick.algafood.domain.repository;



import com.nicezi.patrick.algafood.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {}
