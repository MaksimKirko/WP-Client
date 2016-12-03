package com.github.maximkirko.wpclient.app.models;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
/**
 * Created by MadMax on 20.11.2016.
 */


public class Photo {

    private Long id;
    private byte[] photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }


    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", photo=" + Arrays.toString(photo) +
                '}';
    }

    public Photo() {

    }

//    public static BufferedImage getBufferedImagePhoto(byte[] imageInByte) throws IOException {
//
//        InputStream in = new ByteArrayInputStream(imageInByte);
//        BufferedImage bImageFromConvert = ImageIO.read(in);
//
//        return bImageFromConvert;
//    }
//
//    public static byte[] getByteArrayPhoto(BufferedImage img) throws IOException {
//
//        byte[] imageInByte;
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(img, "png", baos);
//        baos.flush();
//        imageInByte = baos.toByteArray();
//        baos.close();
//
//        return imageInByte;
//    }

}
