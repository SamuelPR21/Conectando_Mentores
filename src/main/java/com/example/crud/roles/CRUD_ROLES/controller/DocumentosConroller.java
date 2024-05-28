package com.example.crud.roles.CRUD_ROLES.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@RestController
//@RequestMapping("/api/fileManager")
public class DocumentosConroller {
//
////    @Autowired
////    private DocumentService documentService;
//
//    @Value("${upload.path}")
//    private String path;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> FileUpload(@RequestParam("file") MultipartFile file) {
//        try {
//            if (!file.getContentType().equals("image/jpeg")) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type. Only JPEG files are allowed.");
//            }
//
//            if (file.getSize() > 1_000_000) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is too large. The size limit is 1 MB.");
//            }
//
//            file.transferTo(new java.io.File(path + file.getOriginalFilename()));
//            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the file: " + e.getMessage());
//        }
//    }
//

//    private final Path fileStorageLocation = Paths.get("C:\\uploads");
//
//    @GetMapping("/file/{id}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
//        try {
//            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//            if (resource.exists() || resource.isReadable()) {
//                return ResponseEntity.ok().body(resource);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    @GetMapping("/files")
//    public  ResponseEntity<List<ArchivosRespuesta>> getListaArchvios(){
//        List<ArchivosRespuesta> files = documentService.getAllfile();
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//
//    }
//
//    @GetMapping("/user/{userId}/files")
//    public ResponseEntity<List<ArchivosRespuesta>> getListaArchviosPorUsuario(@PathVariable int userId) {
//        List<ArchivosRespuesta> files = documentService.getAllFilesByUserId(userId);
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }


}
