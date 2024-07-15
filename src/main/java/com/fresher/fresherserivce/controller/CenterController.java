package com.fresher.fresherserivce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fresher.fresherserivce.model.Center;
import com.fresher.fresherserivce.service.CenterService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    @GetMapping
    public ResponseEntity<List<Center>> getAllCenters() {
        List<Center> centers = centerService.findAll();
        return ResponseEntity.ok(centers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Center> getCenterById(@PathVariable Long id) {
        Optional<Center> center = centerService.findById(id);
        return center.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Center> createCenter(@RequestBody Center center) {
        Center savedCenter = centerService.save(center);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCenter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Center> updateCenter(@PathVariable Long id, @RequestBody Center centerDetails) {
        Center savedCenter = centerService.save(centerDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCenter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable Long id) {
        Optional<Center> centerOptional = centerService.findById(id);
        if (centerOptional.isPresent()) {
            centerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
