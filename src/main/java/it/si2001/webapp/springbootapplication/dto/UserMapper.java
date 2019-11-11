package it.si2001.webapp.springbootapplication.dto;

import it.si2001.webapp.springbootapplication.model.Typology;
import it.si2001.webapp.springbootapplication.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO convertToDTO(User user);

    List<UserDTO> convertToListDTO(List<User> users);

    User convertToModel(UserDTO userDTO);
}
