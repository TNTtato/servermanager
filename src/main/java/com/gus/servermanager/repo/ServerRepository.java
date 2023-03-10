package com.gus.servermanager.repo;

import com.gus.servermanager.domain.Server;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServerRepository extends JpaRepository<Server, Long> {

    Optional<Server> findByIpAddress(String ipAddress);
}
