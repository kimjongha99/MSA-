package com.ggwp.memberservice.client;


import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "squad-service" )
public interface SquadFeignClient {
    @PostMapping("/v1/squad/{uuid}")
    ResponseEntity<?> getSquadByUuid(@Valid @PathVariable String uuid);

}
