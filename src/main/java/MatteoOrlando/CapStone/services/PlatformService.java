package MatteoOrlando.CapStone.services;


import MatteoOrlando.CapStone.entities.Platform;
import MatteoOrlando.CapStone.repositories.PlatformDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatformService {

    @Autowired
    private PlatformDAO platformRepository;

    public List<Platform> findAllPlatforms() {
        return platformRepository.findAll();
    }

    public Platform findPlatformById(Long id) {
        return platformRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Platform not found with id: " + id));
    }

    public Platform savePlatform(Platform platform) {
        return platformRepository.save(platform);
    }

    public void deletePlatform(Long id) {
        platformRepository.deleteById(id);
    }
}
