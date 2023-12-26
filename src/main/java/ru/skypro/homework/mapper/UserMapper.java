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


    public abstract UserInfoDto userToUserInfoDto(Users user);

    public abstract UpdateUserDto userToUpdateUserDto (Users user);
    @Mapping(target = "currentPassword", source = "user.password")
    public abstract SetNewPasswordDto userToSetNewPasswordDto (Users user);

    public abstract UpdateUserImageDto userToUpdateUserImageDto (Users user);

    public abstract Users userInfoDtoToUser (UserInfoDto userInfoDto);

    public abstract Users updateUserDtoToUSer (UpdateUserDto updateUserDto, @MappingTarget Users users);
    @Mapping(target = "password", source = "setNewPasswordDto.newPassword")
    public abstract Users setNewPasswordDtoToUser (SetNewPasswordDto setNewPasswordDto, @MappingTarget Users users);

    public abstract Users updateUserImageDtoToUser (UpdateUserImageDto updateUserImageDto, @MappingTarget Users users);

    protected String map(Image value) {
        if (value != null) {
            return "/users/" + value.getId() + "/image";
        } else {
            return "";
        }
    }

    protected byte[] mapToBytes(Image value) {
        return value.getImage();
    }
    protected Image mapToImage (String value) {
        return imageRepository.findById(value).orElseThrow(RuntimeException::new);
    }

    protected Image mapFromByte(byte[] value) {
         return imageRepository.findByImage(value);
    }
}
