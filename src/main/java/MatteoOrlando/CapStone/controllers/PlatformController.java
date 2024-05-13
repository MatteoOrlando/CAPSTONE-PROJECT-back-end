package MatteoOrlando.CapStone.controllers;

import MatteoOrlando.CapStone.dto.PlatformDTO;
import MatteoOrlando.CapStone.exceptions.BadRequestException;
import MatteoOrlando.CapStone.exceptions.NotFoundException;
import MatteoOrlando.CapStone.services.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platforms")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @GetMapping("/{id}")
    public PlatformDTO getPlatformById(@PathVariable Long id) {
        return platformService.findPlatformById(id);
    }

    @GetMapping("/name/{name}")
    public PlatformDTO getPlatformByName(@PathVariable String name) {
        return platformService.findPlatformByName(name);
    }

    @GetMapping
    public List<PlatformDTO> getAllPlatforms() {
        return platformService.findAllPlatforms();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public PlatformDTO createPlatform(@RequestBody @Validated PlatformDTO platformDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid platform data provided.");
        }
        return platformService.savePlatform(platformDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public PlatformDTO updatePlatform(@PathVariable Long id, @RequestBody @Validated PlatformDTO platformDTO, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid platform data provided.");
        }
        return platformService.updatePlatform(id, platformDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlatform(@PathVariable Long id) {
        platformService.deletePlatform(id);
    }
}
