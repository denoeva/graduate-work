package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skypro.homework.dto.user.SetNewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UpdateUserImageDto;
import ru.skypro.homework.dto.user.UserInfoDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.repository.ImageRepository;

@Mapper(
        componentModel = "spring"
)
public abstract class  UserMapper {

    @Autowired
    ImageRepository imageRepository;


    abstract UserInfoDto userToUserInfoDto(Users user);

    abstract UpdateUserDto userToUpdateUserDto (Users user);
    @Mapping(target = "currentPassword", source = "user.password")
    abstract SetNewPasswordDto userToSetNewPasswordDto (Users user);

    abstract UpdateUserImageDto userToUpdateUserImageDto (Users user);

    abstract Users userInfoDtoToUser (UserInfoDto userInfoDto);

    abstract Users updateUserDtoToUSer (UpdateUserDto updateUserDto, @MappingTarget Users users);
    @Mapping(target = "password", source = "setNewPasswordDto.newPassword")
    abstract Users setNewPasswordDtoToUser (SetNewPasswordDto setNewPasswordDto, @MappingTarget Users users);

    abstract Users updateUserImageDtoToUser (UpdateUserImageDto updateUserImageDto, @MappingTarget Users users);

     String map(Image value) {
        return value.getId();
    }

    byte[] mapToBytes(Image value) {
        return value.getImage();
    }
    Image mapToImage (String value) {
        return imageRepository.findById(value).orElseThrow(RuntimeException::new);
    }

    Image mapFromByte(byte[] value) {
         return imageRepository.findByImage(value);
    }
}
