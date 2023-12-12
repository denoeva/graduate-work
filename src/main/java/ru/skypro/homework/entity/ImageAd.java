package ru.skypro.homework.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class for saving image from advertisements
 */

@Entity
@Table(name = "image_ad")
@Data
public class ImageAd {
    @Id
    private String id;
    private byte[] image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
