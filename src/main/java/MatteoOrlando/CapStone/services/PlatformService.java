package MatteoOrlando.CapStone.services;


import MatteoOrlando.CapStone.entities.Platform;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.repositories.PlatformDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlatformService {

    @Autowired
    private PlatformDAO platformDAO;

    public Platform findPlatformByName(String name) {
        return platformDAO.findByName(name)
                .orElseThrow(() -> new NotFoundException("Nome piattaforma non trovato: " + name));
    }
    public List<Platform> findAllPlatforms() {
        return platformDAO.findAll();
    }

    public Platform findPlatformById(Long id) {
        return platformDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("ID piattaforma non trovato: " + id));
    }

    public Platform savePlatform(Platform platform) {
        return platformDAO.save(platform);
    }

    public void deletePlatform(Long id) {
        platformDAO.deleteById(id);
    }
}
