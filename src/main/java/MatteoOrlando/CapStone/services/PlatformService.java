package MatteoOrlando.CapStone.services;

import MatteoOrlando.CapStone.dto.PlatformDTO;
import MatteoOrlando.CapStone.entities.Platform;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.repositories.PlatformDAO;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlatformService {

    @Autowired
    private PlatformDAO platformDAO;

    private PlatformDTO convertToDTO(Platform platform) {
        return new PlatformDTO(platform.getId(), platform.getName());
    }

    private Platform convertToEntity(PlatformDTO platformDTO) {
        Platform platform = new Platform();
        platform.setId(platformDTO.id());
        platform.setName(platformDTO.name());
        return platform;
    }

    public PlatformDTO findPlatformByName(String name) {
        Platform platform = platformDAO.findByName(name)
                .orElseThrow(() -> new NotFoundException("Nome piattaforma non trovato: " + name));
        return convertToDTO(platform);
    }

    public List<PlatformDTO> findAllPlatforms() {
        List<Platform> platforms = platformDAO.findAll();
        return platforms.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PlatformDTO findPlatformById(Long id) {
        Platform platform = platformDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("ID piattaforma non trovato: " + id));
        return convertToDTO(platform);
    }

    public PlatformDTO savePlatform(PlatformDTO platformDTO) {
        Platform platform = convertToEntity(platformDTO);
        Platform savedPlatform = platformDAO.save(platform);
        return convertToDTO(savedPlatform);
    }

    public PlatformDTO updatePlatform(Long id, PlatformDTO platformDTO) {
        if (!platformDAO.existsById(id)) {
            throw new NotFoundException("Cannot update platform. No platform found with id: " + id);
        }
        Platform platform = convertToEntity(platformDTO);
        platform.setId(id);
        Platform updatedPlatform = platformDAO.save(platform);
        return convertToDTO(updatedPlatform);
    }

    public void deletePlatform(Long id) {
        if (!platformDAO.existsById(id)) {
            throw new NotFoundException("Cannot delete platform. No platform found with id: " + id);
        }
        platformDAO.deleteById(id);
    }

}
