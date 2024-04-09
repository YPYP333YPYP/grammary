package com.example.gachon.domain.lmage;

import com.example.gachon.domain.user.Users;
import com.example.gachon.domain.user.UsersRepository;
import com.example.gachon.global.response.code.resultCode.ErrorStatus;
import com.example.gachon.global.response.exception.handler.ImagesHandler;
import com.example.gachon.global.response.exception.handler.UsersHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImagesService {

    @Value("${image}")
    private String IMAGE_UPLOAD_DIR;

    private final ImagesRepository imagesRepository;
    private final UsersRepository usersRepository;

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }
    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    @Transactional
    public void uploadImage(MultipartFile file, String email){
        try {
            Users user = usersRepository.findByEmail(email).orElseThrow(()->new UsersHandler(ErrorStatus.USER_NOT_FOUND));

            String fileName = createFileName(file.getOriginalFilename());
            System.out.println(fileName);
            String url = IMAGE_UPLOAD_DIR + fileName;
            System.out.println(url);
            File dest = new File(url);
            file.transferTo(dest);

            Images image = Images.builder()
                    .type("USER")
                    .url(url)
                    .name(fileName)
                    .user(user)
                    .build();

            imagesRepository.save(image);
        } catch (IOException e) {
            throw new ImagesHandler(ErrorStatus.IMAGE_UPLOAD_FAIL);
        } catch (IllegalStateException e){
            throw new ImagesHandler(ErrorStatus.IMAGE_NOT_FOUND);
        }


    }
}
