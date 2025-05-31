package com.hunglevi.server.service.impl;

import com.hunglevi.server.entities.Servers;
import com.hunglevi.server.repository.ServersRepository;
import com.hunglevi.server.service.IServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServerService implements IServerService {
    @Autowired
    private ServersRepository serversRepository;
    @Override
    public Page<Servers> findAllServers(Pageable pageable) {
        return serversRepository.findAll(pageable);
    }

    @Override
    public Servers findServerById(Long id) {
        return serversRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Server not found with id: " + id));
    }

    @Override
    public Servers saveServer(Servers server) {
        return serversRepository.save(server);
    }

    @Override
    public void deleteServer(Long id) {
        if (!serversRepository.existsById(id)) {
            throw new RuntimeException("Server not found with id: " + id);
        }
        serversRepository.deleteById(id);
    }
}
