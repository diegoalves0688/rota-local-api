// package com.travel.rotalocal.api;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.travel.rotalocal.api.dto.HealthCheckDTO;
// import com.travel.rotalocal.api.dto.HealthCheckStatus;

// @RestController
// @RequestMapping(value = "/api/healthcheck")
// @CrossOrigin(origins = "*")
// public class HealthCheckController {
    
//     @GetMapping
//     public ResponseEntity healthcheck(){
//         HealthCheckDTO apiStatus = new HealthCheckDTO();
//         apiStatus.setStatus(HealthCheckStatus.OK);
//         return ResponseEntity.ok(apiStatus);
//     }

// }
