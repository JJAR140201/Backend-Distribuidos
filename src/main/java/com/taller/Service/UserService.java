package com.taller.Service;

import com.taller.DTO.*;
import com.taller.Entity.User;
import com.taller.Exceptions.*;
import com.taller.IRepository.IBaseRepository;
import com.taller.IRepository.IUserRepository;
import com.taller.IService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService extends BaseService<User> implements IUserService {
    @Override
    protected IBaseRepository<User, String> getRepository() {
        return repository;
    }

    @Autowired
    private IUserRepository repository;

    @Override
    public List<IUserDto> getListPersonUser() {
        return repository.getListPersonUser();
    }

    @Override
    public List<ILoginDto> getLogin2(String user, String password) throws Exception{
        List<ILoginDto> loginResults = repository.getLogin2(user, password);

        if (loginResults.isEmpty()) {
            throw new Exception("Usuario o la contraseña no son válidos");
        }

        boolean estado = verificarEstado(loginResults);

        if (!estado) {
            throw new Exception("La cuenta se encuentra deshabilitada");
        }

        return loginResults;
    }

    private boolean verificarEstado(List<ILoginDto> loginResults) {
        for (ILoginDto loginDto : loginResults) {
            if (!loginDto.getState()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<IViewDto> getAccessibleViews(String user, String password) throws AccessDeniedException, NoViewsAvailableException {
        List<IViewDto> accessibleViews = repository.getAccessibleViews(user, password);

        if (accessibleViews.isEmpty()) {
            throw new NoViewsAvailableException("No hay vistas accesibles para el usuario.");
        }

        return accessibleViews;
    }

    @Override
    public List<ISystemViewDto> listSystemViews(boolean active) throws NoDataAvailableException {
        List<ISystemViewDto> systemViews = repository.listSystemViews(active);

        if (systemViews.isEmpty()) {
            throw new NoDataAvailableException("No hay datos disponibles para vistas activas o inactivas según el parámetro proporcionado.");
        }

        return systemViews;
    }

    @Override
    public List<IAuditPermissionDto> auditUserPermissions(String username) throws UserNotFoundException, NoPermissionsFoundException {
        User user = repository.findByUser(username);
        if (user == null) {
            throw new UserNotFoundException("El usuario no existe en el sistema.");
        }

        List<IAuditPermissionDto> permissions = repository.auditUserPermissions(username);

        if (permissions.isEmpty()) {
            throw new NoPermissionsFoundException("No existen permisos registrados para ese usuario.");
        }

        return permissions;
    }

}
