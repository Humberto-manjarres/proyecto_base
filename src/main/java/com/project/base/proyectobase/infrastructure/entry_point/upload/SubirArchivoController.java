package com.project.base.proyectobase.infrastructure.entry_point.upload;

import com.project.base.proyectobase.domain.usecase.upload.UploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class SubirArchivoController {

    private final UploadUseCase uploadUseCase;

    @PostMapping("/empleado/upload")
    public ResponseEntity<?> subirArchivoPDF(@RequestParam("archivo")MultipartFile archivo, @RequestParam("cedula")  String cedula){
        if (!"application/pdf".equals(archivo.getContentType())) {
            return ResponseEntity.badRequest().body("Solo se permiten archivos PDF");
        }
        return ResponseEntity.ok(uploadUseCase.cargarArchivo(archivo, cedula));
    }
}
