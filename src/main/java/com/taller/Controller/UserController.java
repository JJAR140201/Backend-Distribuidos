package com.taller.Controller;

import com.taller.DTO.*;
import com.taller.Entity.User;
import com.taller.Exceptions.*;
import com.taller.IService.IUserService;
import com.taller.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/api/user")
public class UserController extends BaseController<User, IUserService> {
    public UserController(IUserService service) {
        super(service, "User");
    }

    @GetMapping("/get-list-person-user")
    public ResponseEntity<ApiResponseDto<List<IUserDto>>> getListPersonUser() {
        try {
            return ResponseEntity.ok(new ApiResponseDto<>("Datos obtenidos", service.getListPersonUser(), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<List<IUserDto>>(e.getMessage(), null, false));
        }
    }
    @GetMapping("get-login2")
    public ResponseEntity<ApiResponseDto<List<ILoginDto>>> getLogin2(@RequestParam String user, @RequestParam String password) {
        try {
            List<ILoginDto> loginDtos = service.getLogin2(user, password);
            if (loginDtos ==  null || loginDtos.isEmpty()){
                return ResponseEntity.ok(new ApiResponseDto<List<ILoginDto>>("Usuario o contraseña incorrectos", false, null));
            }
            return ResponseEntity.ok(new ApiResponseDto<List<ILoginDto>>("Datos obtenidos", true, loginDtos));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<List<ILoginDto>>(e.getMessage(), false, null));
        }
    }

    @GetMapping("get-accessible-views")
    public ResponseEntity<ApiResponseDto<List<IViewDto>>> getAccessibleViews(@RequestParam String user, @RequestParam String password) {
        try {
            List<IViewDto> accessibleViews = service.getAccessibleViews(user, password);
            if (accessibleViews == null || accessibleViews.isEmpty()) {
                return ResponseEntity.ok(new ApiResponseDto<List<IViewDto>>("No hay vistas accesibles para el usuario.", false, null));
            }
            return ResponseEntity.ok(new ApiResponseDto<List<IViewDto>>("Vistas obtenidas", true, accessibleViews));
        } catch (AccessDeniedException e) {
            return ResponseEntity.ok(new ApiResponseDto<List<IViewDto>>(e.getMessage(), false, null));
        } catch (NoViewsAvailableException e) {
            return ResponseEntity.ok(new ApiResponseDto<List<IViewDto>>(e.getMessage(), false, null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<List<IViewDto>>(e.getMessage(), false, null));
        }
    }

    @GetMapping("list-system-views")
    public ResponseEntity<ApiResponseDto<List<ISystemViewDto>>> listSystemViews(@RequestParam boolean active) {
        try {
            List<ISystemViewDto> systemViews = service.listSystemViews(active);
            if (systemViews == null || systemViews.isEmpty()) {
                return ResponseEntity.ok(new ApiResponseDto<>("No hay datos disponibles para vistas activas o inactivas según el parámetro proporcionado.", false, null));
            }
            return ResponseEntity.ok(new ApiResponseDto<>("Datos obtenidos", true, systemViews));
        } catch (NoDataAvailableException e) {
            return ResponseEntity.ok(new ApiResponseDto<>(e.getMessage(), false, null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(e.getMessage(), false, null));
        }
    }

    @GetMapping("audit-user-permissions")
    public ResponseEntity<ApiResponseDto<List<IAuditPermissionDto>>> auditUserPermissions(@RequestParam String username) {
        try {
            List<IAuditPermissionDto> permissions = service.auditUserPermissions(username);
            return ResponseEntity.ok(new ApiResponseDto<>("Datos obtenidos", true, permissions));
        } catch (UserNotFoundException | NoPermissionsFoundException e) {
            return ResponseEntity.ok(new ApiResponseDto<>(e.getMessage(), false, null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<>(e.getMessage(), false, null));
        }
    }
}
