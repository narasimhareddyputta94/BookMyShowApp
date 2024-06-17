package com.bookmyshow.demo.controllers;

import com.bookmyshow.demo.models.Region;
import com.bookmyshow.demo.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/regions")
public class RegionController {
    @Autowired
    private RegionRepository regionRepository;

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        return new ResponseEntity<>(regionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRegionById(@PathVariable Long id) {
        Optional<Region> region = regionRepository.findById(id);
        if (region.isPresent()) {
            return new ResponseEntity<>(region.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Region not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        Region savedRegion = regionRepository.save(region);
        return new ResponseEntity<>(savedRegion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRegion(@PathVariable Long id, @RequestBody Region regionDetails) {
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (!regionOptional.isPresent()) {
            return new ResponseEntity<>("Region not found", HttpStatus.NOT_FOUND);
        }
        Region region = regionOptional.get();
        region.setName(regionDetails.getName());
        regionRepository.save(region);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegion(@PathVariable Long id) {
        if (!regionRepository.existsById(id)) {
            return new ResponseEntity<>("Region not found", HttpStatus.NOT_FOUND);
        }
        regionRepository.deleteById(id);
        return new ResponseEntity<>("Region deleted successfully", HttpStatus.OK);
    }
}
