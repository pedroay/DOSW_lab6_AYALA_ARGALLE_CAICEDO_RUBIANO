package edu.eci.dosw.oficioya.controller;

import edu.eci.dosw.oficioya.dto.WorkerRegistrationDTO;
import edu.eci.dosw.oficioya.dto.WorkerResponseDTO;
import edu.eci.dosw.oficioya.dto.WorkerUpdateDTO;
import edu.eci.dosw.oficioya.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabajadores")
public class WorkerController {

    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    /**
     * Listar trabajadores (Read varios).
     * Permite filtrar por zona y por defecto solo muestra trabajadores activos.
     */
    @GetMapping
    public ResponseEntity<List<WorkerResponseDTO>> getAllWorkers(
            @RequestParam(required = false) String zona,
            @RequestParam(required = false, defaultValue = "false") boolean incluirInactivos) {
        List<WorkerResponseDTO> workers = workerService.findAll(zona, !incluirInactivos);
        return ResponseEntity.ok(workers);
    }

    /**
     * Consultar perfil de un trabajador específico (Read individual).
     */
    @GetMapping("/{id}")
    public ResponseEntity<WorkerResponseDTO> getWorkerById(@PathVariable int id) {
        WorkerResponseDTO worker = workerService.findById(id);
        return ResponseEntity.ok(worker);
    }

    /**
     * Registrar un nuevo trabajador (Create).
     * Los trabajadores se crean por defecto con estado Activo.
     * Retorna HTTP 201 Created.
     */
    @PostMapping
    public ResponseEntity<WorkerResponseDTO> createWorker(@RequestBody WorkerRegistrationDTO workerDTO) {
        WorkerResponseDTO created = workerService.create(workerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Actualizar datos del trabajador (Update).
     * Si el trabajador está inactivo, se rechaza la actualización según la regla de negocio.
     */
    @PutMapping("/{id}")
    public ResponseEntity<WorkerResponseDTO> updateWorker(@PathVariable int id, @RequestBody WorkerUpdateDTO updateDTO) {
        WorkerResponseDTO updated = workerService.update(id, updateDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * Inactivación lógica mediante DELETE (Delete -> Inactivar).
     * No existe borrado físico; cambia el estado a Inactivo sin tocar el User.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<WorkerResponseDTO> deleteWorker(@PathVariable int id) {
        WorkerResponseDTO inactivated = workerService.inactivate(id);
        return ResponseEntity.ok(inactivated);
    }

    /**
     * Endpoint semántico alternativo para inactivación lógica directa.
     */
    @PatchMapping("/{id}/inactivar")
    public ResponseEntity<WorkerResponseDTO> inactivateWorker(@PathVariable int id) {
        WorkerResponseDTO inactivated = workerService.inactivate(id);
        return ResponseEntity.ok(inactivated);
    }
}
