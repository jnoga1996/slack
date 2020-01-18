package com.slack.slack.controllers;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.slack.slack.storage.StorageFileNotFoundException;
import com.slack.slack.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping("/Files")
public class FileUploadController {

    private static final Logger logger = Logger.getLogger(FileUploadController.class.getName());
    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public List<String> listUploadedFiles() {
        List<String> files = storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList());

        return files;
    }

    @GetMapping("/{id}")
    public List<String> listUploadedFiles(@PathVariable("id") Long id) {
        List<String> files = storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList());
        files = files.stream().filter(f -> f.contains(id + ".")).collect(Collectors.toList());

        return files;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public HttpStatus handleFileUpload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        logger.info("You successfully uploaded " + file.getOriginalFilename());
        return HttpStatus.OK;
    }

    @PostMapping("/{id}")
    public HttpStatus handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        if (id == null) {
            logger.info("Id is null, file saving failed");
            return HttpStatus.BAD_REQUEST;
        }
        storageService.store(file, id);
        logger.info("You successfully uploaded " + file.getOriginalFilename());
        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/{filename}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus delete(@PathVariable("filename") String filename) {
        if (filename == null) {
            return HttpStatus.NOT_FOUND;
        }
        List<String> files = storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList());
        files = files.stream().filter(f -> f.contains(filename)).collect(Collectors.toList());
        String path = "";
        try {
            path = files.get(0).split("/")[5];
        } catch (ArrayIndexOutOfBoundsException ex) {
            logger.info("Failed to parse file");
        }
        boolean isError = storageService.delete(path);
        if (isError) {
            logger.info("Failed to remove file: " + filename);
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}