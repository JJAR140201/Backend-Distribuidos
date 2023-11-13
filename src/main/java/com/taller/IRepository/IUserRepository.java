package com.taller.IRepository;

import com.taller.DTO.*;
import com.taller.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends IBaseRepository<User,String>{
    @Query(value = "{ 'state': true }")
    List<User> findAllUsersWithStateTrue();

    @Query(value = "["
            + "{'$match': { 'state': true }},"
            + "{'$lookup': { 'from': 'person', 'localField': 'personId', 'foreignField': '_id', 'as': 'person' }},"
            + "{'$unwind': '$person'},"
            + "{'$project': { '_id': '$person._id', 'persona': { '$concat': ['$person.name', ' ', '$person.lastname'] }, 'userId': '$_id', 'user': 1 }}"
            + "]")
    List<IUserDto> getListPersonUser();

    @Query(value = " SELECT  "
            + "	user, "
            + "    1 as autorizacion, "
            + "    state "
            + " FROM  "
            + "		user "
            + " WHERE  "
            + "	user = :user AND  "
            + "    password = :password; ", nativeQuery = true)
    List<ILoginDto> getLogin2(@Param("user") String user, @Param("password") String password);

    @Query(value = "SELECT v.name, v.description, v.route " +
            "FROM users u " +
            "JOIN authorizations a ON u.user_id = a.user_id " +
            "JOIN user_views uv ON u.user_id = uv.user_id " +
            "JOIN views v ON uv.view_id = v.view_id " +
            "WHERE u.user = :user " +
            "AND u.password = :password " +
            "AND a.authorization = 1", nativeQuery = true)
    List<IViewDto> getAccessibleViews(@Param("user") String user, @Param("password") String password);

    @Query(value = "SELECT v.name AS viewName, m.name AS moduleName, r.name AS roleName " +
            "FROM system_view v " +
            "JOIN module m ON v.module_id = m.module_id " +
            "JOIN role r ON v.role_id = r.role_id " +
            "WHERE v.active = :active", nativeQuery = true)
    List<ISystemViewDto> listSystemViews(@Param("active") boolean active);

    @Query(value = "SELECT CONCAT(u.username, ' ', r.name) AS permission, " +
            "r.name AS role, " +
            "r.status AS roleStatus, " +
            "m.name AS module, " +
            "m.status AS moduleStatus, " +
            "v.name AS view, " +
            "v.status AS viewStatus " +
            "FROM user u " +
            "JOIN user_role ur ON u.user_id = ur.user_id " +
            "JOIN role r ON ur.role_id = r.role_id " +
            "JOIN role_module rm ON r.role_id = rm.role_id " +
            "JOIN module m ON rm.module_id = m.module_id " +
            "JOIN module_view mv ON m.module_id = mv.module_id " +
            "JOIN view v ON mv.view_id = v.view_id " +
            "WHERE u.username = :username", nativeQuery = true)
    List<IAuditPermissionDto> auditUserPermissions(@Param("username") String username);

}
