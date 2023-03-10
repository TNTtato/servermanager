package com.gus.servermanager.resource;

import com.gus.servermanager.domain.Response;
import com.gus.servermanager.domain.Server;
import com.gus.servermanager.enumeration.Status;
import com.gus.servermanager.service.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {

    private final ServerServiceImpl serverService;

    @GetMapping
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("servers", serverService.list(30)))
                        .message("Servers retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("servers", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping Failure")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
