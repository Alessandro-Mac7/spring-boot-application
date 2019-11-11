package it.si2001.webapp.springbootapplication.dto;

import it.si2001.webapp.springbootapplication.model.Typology;
import it.si2001.webapp.springbootapplication.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RegisterUserMapper {

    RegisterUserDTO convertToDTO(User user);

    @Named("convertToModel")
    static User convertToModel(RegisterUserDTO registerUserDTO, Typology typology) throws ParseException {
        User user = new User();
        user.setName(registerUserDTO.getName());
        user.setEmail(registerUserDTO.getEmail());
        user.setPassword("");
        user.setDate(registerUserDTO.getDate());
        user.setLastName(registerUserDTO.getLastName());
        user.setTypology(typology);
        return user;
    }
}
