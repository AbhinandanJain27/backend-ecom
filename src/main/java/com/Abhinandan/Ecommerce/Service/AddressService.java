package com.Abhinandan.Ecommerce.Service;

import com.Abhinandan.Ecommerce.Entity.Address;

import java.util.List;

public interface AddressService {
    Address saveAddress(Address address);
    List<Address> getAllAddressesOfUser();
    Address updateAddress(long id, Address address);
    boolean deleteAddress(long id);
}
