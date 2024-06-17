package com.bookmyshow.demo.services;

import com.bookmyshow.demo.models.Region;
import com.bookmyshow.demo.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<Region> getRegionById(Long id) {
        return regionRepository.findById(id);
    }

    public Region createRegion(Region region) {
        return regionRepository.save(region);
    }

    public Region updateRegion(Long id, Region regionDetails) {
        Optional<Region> regionOptional = regionRepository.findById(id);
        if (regionOptional.isPresent()) {
            Region region = regionOptional.get();
            region.setName(regionDetails.getName());
            return regionRepository.save(region);
        }
        return null;
    }

    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }
}
