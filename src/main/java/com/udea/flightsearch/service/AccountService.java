package com.udea.flightsearch.service;

import com.udea.flightsearch.model.Account;
import com.udea.flightsearch.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private IAccountRepository accountRepository;

    // Funcion para obtener todas las cuentas
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Funcion para obtener una cuenta por su ID
    public Account getAccountById(Long id) {
        Optional<Account> accountOpt = accountRepository.findById(id);
        return accountOpt.orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }

    // Funcion para crear o actualizar una cuenta
    public Account createOrUpdateAccount(Account account) {
        return accountRepository.save(account);
    }

    // Funcion para eliminar una cuenta por su ID
    public void deleteAccount(Long id) {
        // Manejo de error: verificar si existe antes de eliminar
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete Account. Not found with id: " + id);
        }
        accountRepository.deleteById(id);
    }
}
