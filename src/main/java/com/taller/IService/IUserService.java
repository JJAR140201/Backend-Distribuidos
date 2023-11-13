package com.taller.IService;

import com.taller.DTO.*;
import com.taller.Entity.User;
import com.taller.Exceptions.NoDataAvailableException;
import com.taller.Exceptions.NoPermissionsFoundException;
import com.taller.Exceptions.UserNotFoundException;
import com.taller.Service.UserService;

import java.util.List;

public interface IUserService extends IBaseService<User> {
    List<IUserDto> getListPersonUser();

    List<ILoginDto> getLogin2(String user, String password) throws Exception;
    List<IViewDto> getAccessibleViews(String user, String password) throws Exception;

    List<ISystemViewDto> listSystemViews(boolean active) throws NoDataAvailableException;

    List<IAuditPermissionDto> auditUserPermissions(String username) throws UserNotFoundException, NoPermissionsFoundException;
}
