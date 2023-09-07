package vn.edu.vtiacademy.demolesson7.controller;

import org.mapstruct.Mapper;
import vn.edu.vtiacademy.demolesson7.model.Address;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    List<Address> toAddresses(List<AddressReq> addressReqs);

    Address toAddress(AddressReq addressReq);

    AddressResp toAddressResp(Address address);

    List<AddressResp> toAddressResp(List<Address> addresses);
}
