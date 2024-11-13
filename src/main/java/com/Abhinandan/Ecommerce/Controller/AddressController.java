package com.Abhinandan.Ecommerce.Controller;

import com.Abhinandan.Ecommerce.Entity.Address;
import com.Abhinandan.Ecommerce.Filters.JwtFilter;
import com.Abhinandan.Ecommerce.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private JwtFilter helper;
    @Autowired
    private AddressService addressService;
    @PostMapping("/addAddress")
    public Address createAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @GetMapping("/usersAddresses")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddressesOfUser();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable long id, @RequestBody Address addressDetails) {
        Address updatedAddress = addressService.updateAddress(id, addressDetails);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

}