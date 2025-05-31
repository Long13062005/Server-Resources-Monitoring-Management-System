package com.hunglevi.server.service;


import com.hunglevi.server.entities.Servers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IServerService {
    // Define methods that the server service should implement
    // For example:
    Page<Servers> findAllServers(Pageable pageable);
    Servers findServerById(Long id);
    Servers saveServer(Servers server);
    void deleteServer(Long id);
}
