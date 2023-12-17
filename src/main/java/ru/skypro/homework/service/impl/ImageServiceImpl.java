package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.service.ImageService;
@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public Image createImage(MultipartFile image) {
        return null;
    }

    @Override
    public Image updateImage(MultipartFile newImage, Image oldImage) {
        return null;
    }

    @Override
    public byte[] getImage(String id) {
        return new byte[0];
    }
}
