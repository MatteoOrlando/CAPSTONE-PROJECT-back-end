package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.dto.PlatformDTO;
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

    public Platform savePlatform(PlatformDTO platformDTO) {
        Platform platform = new Platform();
        platform.setName(platformDTO.name());
        return platformDAO.save(platform);
    }

    public Platform findPlatformById(Long id) {
        return platformDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Platform not found with id: " + id));
    }

    public Platform findPlatformByName(String name) {
        return platformDAO.findByName(name)
                .orElseThrow(() -> new NotFoundException("Platform not found with name: " + name));
    }

    public List<Platform> findAllPlatforms() {
        return platformDAO.findAll();
    }

    public Platform updatePlatform(Long id, PlatformDTO platformDTO) {
        Platform platform = platformDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Platform not found with id: " + id));
        platform.setName(platformDTO.name());
        return platformDAO.save(platform);
    }

    public void deletePlatform(Long id) {
        platformDAO.deleteById(id);
    }
}
