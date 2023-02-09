package com.override.myhomeserver.repository;

import com.override.myhomeserver.model.Socket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocketRepository extends JpaRepository<Socket, String> {
}
