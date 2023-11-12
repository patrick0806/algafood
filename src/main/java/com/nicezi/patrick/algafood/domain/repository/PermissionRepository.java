package com.nicezi.patrick.algafood.domain.repository;



import com.nicezi.patrick.algafood.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {
    List<Permission> list();
    Permission findById(Long id);
    Permission save(Permission city);
    void remove(Long id);
}
