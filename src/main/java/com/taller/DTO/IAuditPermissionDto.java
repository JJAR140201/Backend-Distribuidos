package com.taller.DTO;

public interface IAuditPermissionDto {
    String getPermission(); // Usuario Rol
    String getRole();
    String getRoleStatus();
    String getModule();
    String getModuleStatus();
    String getView();
    String getViewStatus();
}
