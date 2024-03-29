package hu.bnpi.dhte.inventory.inventoryitem.controller;

import hu.bnpi.dhte.inventory.inventoryitem.dtos.*;
import hu.bnpi.dhte.inventory.inventoryitem.service.InventoryItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/items")
public class InventoryItemController {

    private InventoryItemService service;

    @GetMapping(value = "/all")
    public List<InventoryItemDetails> findAllItems() {
        return service.findAllItems();
    }

    @GetMapping(value = "/{inventoryNumber}")
    public InventoryItemDetails findByInventoryNumber(@PathVariable String inventoryNumber) {
        return service.findByInventoryNumber(inventoryNumber);
    }

    @GetMapping(value = "/responsible/{responsibleNumber}")
    public List<InventoryItemDetails> findAllByResponsibleNumber(@PathVariable String responsibleNumber) {
        return service.findAllByResponsibleNumber(responsibleNumber);
    }

    @GetMapping(value = "/responsible")
    public List<InventoryItemDetails> findAllByResponsibleName(@RequestParam(value = "name") String nameSubstring) {
        return service.findAllByResponsibleName(nameSubstring);
    }

    @GetMapping(value = "/kits")
    public List<KitDetails> findAllKits() {
        return service.findAllKits();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryItemDetails saveInventoryItem(@Valid @RequestBody CreateInventoryItemCommand command) {
        return service.saveInventoryItem(command);
    }

    @PostMapping(value = "/kits")
    @ResponseStatus(HttpStatus.CREATED)
    public KitDetails saveKit(@Valid @RequestBody CreateKitCommand command) {
        return service.saveKit(command);
    }

    @PutMapping(value = "/{inventoryNumber}")
    public InventoryItemDetails updateInventoryItem(@PathVariable("inventoryNumber") String inventoryNumber, @Valid @RequestBody UpdateInventoryItemCommand command) {
        return service.updateInventoryItem(inventoryNumber, command);
    }

    @PutMapping(value = "/{inventoryNumber}/shortage")
    public InventoryItemDetails setShortage(@PathVariable("inventoryNumber") String inventoryNumber, @RequestParam Optional<Boolean> isDisposal, @RequestParam Optional<Boolean> isDeficit) {
        return service.setShortage(inventoryNumber, isDisposal, isDeficit);
    }

    @DeleteMapping(value = "/kits/{kitId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeKit(@PathVariable("kitId") long kitId) {
        service.removeKit(kitId);
    }
}
