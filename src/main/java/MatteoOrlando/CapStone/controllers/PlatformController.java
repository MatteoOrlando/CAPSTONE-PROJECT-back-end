package MatteoOrlando.CapStone.controllers;

import MatteoOrlando.CapStone.dto.PlatformDTO;
import MatteoOrlando.CapStone.entities.Platform;
import MatteoOrlando.CapStone.exceptions.BadRequestException;
import MatteoOrlando.CapStone.services.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/platforms")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public PlatformDTO createPlatform(@RequestBody @Validated PlatformDTO platformDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid platform data provided.");
        }
        Platform platform = platformService.savePlatform(platformDTO);
        return convertToDTO(platform);
    }

    @GetMapping("/{id}")
    public PlatformDTO getPlatformById(@PathVariable Long id) {
        Platform platform = platformService.findPlatformById(id);
        return convertToDTO(platform);
    }

    @GetMapping("/name/{name}")
    public PlatformDTO getPlatformByName(@PathVariable String name) {
        Platform platform = platformService.findPlatformByName(name);
        return convertToDTO(platform);
    }

    @GetMapping
    public List<PlatformDTO> getAllPlatforms() {
        List<Platform> platforms = platformService.findAllPlatforms();
        return platforms.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public PlatformDTO updatePlatform(@PathVariable Long id, @RequestBody @Validated PlatformDTO platformDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid platform data provided.");
        }
        Platform platform = platformService.updatePlatform(id, platformDTO);
        return convertToDTO(platform);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlatform(@PathVariable Long id) {
        platformService.deletePlatform(id);
    }

    private PlatformDTO convertToDTO(Platform platform) {
        return new PlatformDTO(platform.getId(), platform.getName());
    }
}
