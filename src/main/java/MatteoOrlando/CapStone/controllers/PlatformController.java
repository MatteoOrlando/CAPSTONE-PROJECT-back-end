package MatteoOrlando.CapStone.controllers;

import MatteoOrlando.CapStone.entities.Platform;
import MatteoOrlando.CapStone.services.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platforms")
public  class PlatformController {

    @Autowired
    private PlatformService platformService;

    @GetMapping
    public List<Platform> getAllPlatforms(){
        return platformService.findAllPlatforms();
    }

    @GetMapping("/{id}")
    public Platform getPlatformById(@PathVariable Long id) {
        return platformService.findPlatformById(id);
        }

    @PostMapping
    public Platform createPlatform(@RequestBody Platform platform) {
        return platformService.savePlatform(platform);
    }

    @PutMapping("/{id}")
    public Platform updatePlatform(@PathVariable Long id, @RequestBody Platform platform) {
        platform.setId(id);
        return platformService.savePlatform(platform);
    }

    @DeleteMapping("/{id}")
    public void deletePlatform(@PathVariable Long id) {
        platformService.deletePlatform(id);
    }

}
