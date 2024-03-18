package org.teamraccoon.dreamfusion.images;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.teamraccoon.dreamfusion.generic.IStorageService;
import org.teamraccoon.dreamfusion.messages.ResponseMessage;

@RestController
@RequestMapping(path = "${api-endpoint}")
public class ImageController {

    @Autowired
    IStorageService service;

    @PostMapping(path = "/images/uploadImages/{id}")
    ResponseEntity<ResponseMessage> uploadImages(@PathVariable("id") @NonNull Long id, @RequestParam("files") MultipartFile[] files) {

        String message = "";

        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                service.save(id, file);
                fileNames.add(file.getOriginalFilename());
            });

            message = "File with the names " + fileNames + " are uploaded successfully: ";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}