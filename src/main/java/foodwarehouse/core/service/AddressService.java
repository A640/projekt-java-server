package foodwarehouse.core.service;

import foodwarehouse.core.data.address.Address;
import foodwarehouse.core.data.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<Address> createAddress(String country, String town, String postalCode, String buildingNumber, String street, String apartmentNumber) {
        return addressRepository.createAddress(country, town, postalCode, buildingNumber, street, apartmentNumber);
    }

    public Optional<Address> updateAddress(int addressId, String country, String town, String postalCode, String buildingNumber, String street, String apartmentNumber) {
        return addressRepository.updateAddress(addressId, country, town, postalCode, buildingNumber, street, apartmentNumber);
    }

    public boolean deleteAddress(int addressId) {
        return addressRepository.deleteAddress(addressId);
    }

    public Optional<Address> findAddressById(int addressId) {
        return addressRepository.findAddressById(addressId);
    }
}
