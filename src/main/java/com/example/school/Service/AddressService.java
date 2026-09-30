package com.example.school.Service;

import com.example.school.API.APIException;
import com.example.school.DTO.AddressDTO;
import com.example.school.Model.Address;
import com.example.school.Model.Teacher;
import com.example.school.Repository.AddressRepository;
import com.example.school.Repository.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;
    public List<Address> get(){
        return addressRepository.findAll();
    }

    public void add(AddressDTO addressDTO){
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacherId());
        if (teacher == null)
            throw new APIException("teacher not exist");

        Address address = new Address(null, addressDTO.getArea(), addressDTO.getStreet(), addressDTO.getBuildingNumber(), teacher);
        addressRepository.save(address);
    }

    public void update(AddressDTO addressDTO){
        Address address = addressRepository.findAddressById(addressDTO.getTeacherId());

        if (address == null)
            throw new APIException("address not exist");

        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(address.getBuildingNumber());

        addressRepository.save(address);
    }

    public void delete(Integer id){
        Address address = addressRepository.findAddressById(id);
        if (address == null)
            throw new APIException("address not found");
        addressRepository.delete(address);
    }

}
