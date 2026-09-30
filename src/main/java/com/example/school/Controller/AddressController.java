package com.example.school.Controller;

import com.example.school.API.APIResponse;
import com.example.school.DTO.AddressDTO;
import com.example.school.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(addressService.get());

    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid AddressDTO addressDTO){
        addressService.add(addressDTO);
        return ResponseEntity.status(200).body(new APIResponse("added successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid AddressDTO addressDTO){
        addressService.update(addressDTO);
        return ResponseEntity.status(200).body(new APIResponse("updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        addressService.delete(id);
        return ResponseEntity.status(200).body(new APIResponse("deleted successfully"));
    }
}
