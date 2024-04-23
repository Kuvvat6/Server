package ru.finance.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.finance.my.controller.path.Path;
import ru.finance.my.entity.Transfer;
import ru.finance.my.entity.User;
import ru.finance.my.service.TransferService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(Path.TRANSFER_PATH)
public class TransferController {

  @Autowired
  private TransferService transferService;

  @GetMapping
  public List<Transfer> getAll(
      @RequestParam("before-date") String beforeDate,
      @RequestParam("after-date") String afterDate,
      UsernamePasswordAuthenticationToken token
  ) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate before = LocalDate.parse(beforeDate, formatter);
    LocalDate after = LocalDate.parse(afterDate, formatter);
    User user = (User)token.getPrincipal();
    return transferService.getAll(before, after, user.getId());
  }

  @PostMapping
  public ResponseEntity<Transfer> createNew(@RequestBody Transfer transfer) {
    transferService.createNew(transfer);
    return ResponseEntity.status(HttpStatus.CREATED).body(transfer);
  }

  @DeleteMapping("/{transferId}")
  public void deleteById(@PathVariable Long transferId) {
    transferService.deleteById(transferId);
  }

  @PutMapping("/{transferId}")
  public Transfer updateById(@PathVariable Long transferId, @RequestBody Transfer transfer) {
    transfer.setId(transferId);
    transferService.update(transfer);
    return transfer;
  }

  @GetMapping("/{transferId}")
  public Transfer getById(@PathVariable Long transferId) {
    return transferService.getById(transferId);
  }
}
