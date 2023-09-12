package vn.edu.vtiacademy.demolesson7.controller;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.vtiacademy.demolesson7.model.Address;

import java.util.List;

@Mapper
public interface AddressInstanceMapper {
    AddressInstanceMapper INSTANCE = Mappers.getMapper(AddressInstanceMapper.class);

    List<Address> toAddresses(List<AddressReq> addressReqs);

    Address toAddress(AddressReq addressReq);

    AddressResp toAddressResp(Address address);

    List<AddressResp> toAddressResp(List<Address> addresses);
}
