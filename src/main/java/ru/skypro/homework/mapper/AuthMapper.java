package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.auth_register.Login;
import ru.skypro.homework.dto.auth_register.Register;
import ru.skypro.homework.entity.Users;

@Mapper(
        componentModel = "spring"
)
public abstract class AuthMapper {

    public abstract Login userToLogin (Users users);

    public abstract Users loginToUser (Login login, @MappingTarget Users users);

    public abstract Users registerToUser (Register register);
}
