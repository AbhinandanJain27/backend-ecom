package com.Abhinandan.Ecommerce.Service.IMPL;

import com.Abhinandan.Ecommerce.Entity.Address;
import com.Abhinandan.Ecommerce.Entity.User;
import com.Abhinandan.Ecommerce.Exceptions.AddressNotFoundException;
import com.Abhinandan.Ecommerce.Exceptions.UnauthorizedAccessException;
import com.Abhinandan.Ecommerce.Repository.AddressRepository;
import com.Abhinandan.Ecommerce.Repository.UserRepository;
import com.Abhinandan.Ecommerce.Service.AddressService;
import com.Abhinandan.Ecommerce.Utils.EmailContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Address saveAddress(Address address) {
        String email = EmailContext.getEmail();
        User user = userRepository.findByEmail(email);
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddressesOfUser() {
        String email = EmailContext.getEmail();
        return addressRepository.findAllByUserEmail(email);
    }

    @Override
    public Address updateAddress(long id, Address addressDetails) {
        Optional<Address> retrievedAddress = addressRepository.findById(id);
        String email = EmailContext.getEmail();
        if(retrievedAddress.isPresent()){
            Address address = retrievedAddress.get();
            if (!address.getUser().getEmail().equals(email)) {
                throw new UnauthorizedAccessException();
            }
            // Update only non-null fields
            if (addressDetails.getHouseNumber() != null) {
                address.setHouseNumber(addressDetails.getHouseNumber());
            }
            if (addressDetails.getStreet1() != null) {
                address.setStreet1(addressDetails.getStreet1());
            }
            if (addressDetails.getStreet2() != null) {
                address.setStreet2(addressDetails.getStreet2());
            }
            if (addressDetails.getCity() != null) {
                address.setCity(addressDetails.getCity());
            }
            if (addressDetails.getState() != null) {
                address.setState(addressDetails.getState());
            }
            if (addressDetails.getCountry() != null) {
                address.setCountry(addressDetails.getCountry());
            }
            if (addressDetails.getPinCode() != 0) { // Assuming pinCode is an int, check for default value
                address.setPinCode(addressDetails.getPinCode());
            }

            return addressRepository.save(address);
        }else{
            throw new AddressNotFoundException();
        }
    }

    @Override
    public boolean deleteAddress(long id) {
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()){
            addressRepository.delete(address.get());
            return true;
        }
        return false;
    }
}
