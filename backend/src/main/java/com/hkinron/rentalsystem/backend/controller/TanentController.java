package com.hkinron.rentalsystem.backend.controller;

import com.hkinron.rentalsystem.backend.model.Tenant;
import com.hkinron.rentalsystem.backend.service.TanentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TanentController {

    private TanentService tanentService;

    public TanentController(TanentService tanentService) {
        this.tanentService = tanentService;
    }

    @PostMapping(path = "/tenant")
    public Tenant addNewTenant(@RequestBody Tenant tenant) {
        Tenant tenantInDb = tanentService.addNewUser(tenant);
        log.info("Successfully create tenant " + tenant);
        return tenantInDb;
    }

    @GetMapping(path = "/tenant/{id}")
    public Tenant getTenantById(@PathVariable("id") long id) {
        Tenant tenant = tanentService.getUserById(id);
        log.info("Successfully get tenant " + tenant + " from DB by id " + id);
        return tenant;
    }

    @GetMapping(path = "/tenants")
    public Page<Tenant> getAllTenants(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                          Pageable pageable) {
        Page<Tenant> users = tanentService.getAllUsers(pageable);
        log.info("Get " + users.getTotalElements() + " tenants from DB");
        return users;
    }

    @DeleteMapping(path = "/tenant")
    public long deleteTenant(@RequestParam long userId) {
        tanentService.deleteUser(userId);
        log.info("successfully deleted tenant by id " + userId + "from DB");
        return userId;
    }


}
