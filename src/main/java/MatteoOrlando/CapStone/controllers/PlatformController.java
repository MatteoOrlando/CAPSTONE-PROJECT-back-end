package MatteoOrlando.CapStone.controllers;

import MatteoOrlando.CapStone.entities.Platform;
import MatteoOrlando.CapStone.exceptions.BadRequestException;
import MatteoOrlando.CapStone.services.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@RequestMapping("/platforms")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @GetMapping("/{id}")
    public Platform getPlatformById(@PathVariable Long id) {
        return platformService.findPlatformById(id);
    }

    @GetMapping("/name/{name}")
    public Platform getPlatformByName(@PathVariable String name) {
        return platformService.findPlatformByName(name);
    }

    @GetMapping
    public List<Platform> getAllPlatforms() {
        return platformService.findAllPlatforms();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Platform createPlatform(@RequestBody @Validated Platform platform, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid platform data provided.");
        }
        return platformService.savePlatform(platform);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Platform updatePlatform(@PathVariable Long id, @RequestBody @Validated Platform platform, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException("Invalid platform data provided.");
        }
        platform.setId(id);
        return platformService.savePlatform(platform);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlatform(@PathVariable Long id) {
        platformService.deletePlatform(id);
    }
}
