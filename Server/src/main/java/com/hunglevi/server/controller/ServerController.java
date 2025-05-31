package com.hunglevi.server.controller;

import com.hunglevi.server.entities.Servers;
import com.hunglevi.server.service.impl.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/server")
@CrossOrigin
public class ServerController {
    @Autowired
    private ServerService serverService;

    // Define endpoints for server operations here
    // For example:
    @GetMapping()
    public ResponseEntity<?> getServers(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "4") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Servers> servers = serverService.findAllServers(pageable);

            if (servers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return new ResponseEntity<>(servers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching contracts", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServerById(@PathVariable Long id) {
        try {
            Servers server = serverService.findServerById(id);
            return new ResponseEntity<>(server, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Server not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createServer(@RequestBody Servers server) {
        try {
            Servers createdServer = serverService.saveServer(server);
            return new ResponseEntity<>(createdServer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the server", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateServer(@PathVariable Long id, @RequestBody Servers server) {
        try {
            server.setId(id); // Ensure the ID is set for the update
            Servers updatedServer = serverService.saveServer(server);
            return new ResponseEntity<>(updatedServer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the server", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServer(@PathVariable Long id) {
        try {
            serverService.deleteServer(id);
            return new ResponseEntity<>("Server deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Server not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
