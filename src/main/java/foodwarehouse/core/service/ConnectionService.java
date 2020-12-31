package foodwarehouse.core.service;

import foodwarehouse.database.jdbc.connection.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionService implements ConnectionRepository {
    private final ConnectionRepository connectionRepository;

    @Autowired
    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public boolean isReachable() {
        return connectionRepository.isReachable();
    }
}