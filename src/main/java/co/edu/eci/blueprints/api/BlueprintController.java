package co.edu.eci.blueprints.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/blueprints")
@Tag(name = "Blueprints Simple", description = "API simplificada para gestión de blueprints")
public class BlueprintController {

    @Operation(summary = "Obtiene todos los blueprints")
    @ApiResponse(responseCode = "200", description = "Lista de blueprints obtenida exitosamente")
    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_blueprints.read')")

    public List<Map<String, String>> list() {
        return List.of(
            Map.of("id", "b1", "name", "Casa de campo"),
            Map.of("id", "b2", "name", "Edificio urbano")
        );
    }
    @Operation(summary = "Crea un nuevo blueprint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blueprint creado exitosamente"),
            @ApiResponse(responseCode = "403", description = "No tiene permisos para crear blueprints")
    })
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_blueprints.write')")
    public Map<String, String> create(@RequestBody Map<String, String> in) {
        return Map.of("id", "new", "name", in.getOrDefault("name", "nuevo"));
    }
}
