package com.hkinron.rentalsystem.backend.controller;

import com.hkinron.rentalsystem.backend.entity.Tenant;
import com.hkinron.rentalsystem.backend.service.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("tenants")
public class TenantController {

    private TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping(path = "/")
    public Tenant createTenant(@RequestBody Tenant tenant) {
        Tenant tenantInDb = tenantService.createUser(tenant);
        log.info(String.format("Successfully create tenant %s.", tenant));
        return tenantInDb;
    }

    @GetMapping(path = "/{id}")
    public Tenant getTenantById(@PathVariable("id") long id) {
        Tenant tenant = tenantService.getUserById(id);
        log.info(String.format("Successfully get tenant %s from DB by id %d.", tenant, id));
        return tenant;
    }

    @GetMapping(path = "/")
    public Page<Tenant> getAllTenants(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)
                                          Pageable pageable) {
        Page<Tenant> users = tenantService.getAllUsers(pageable);
        log.info(String.format("Get %d tenants.", users.getTotalElements()));
        return users;
    }

    @DeleteMapping(path = "/{id}")
    public long deleteTenantById(@PathVariable("id") long id) {
        tenantService.deleteUserById(id);
        log.info(String.format("successfully deleted tenant by id %d", id));
        return id;
    }


}
