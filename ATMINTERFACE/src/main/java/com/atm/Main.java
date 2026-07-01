package com.atm;

import com.atm.controller.ATMController;
import com.atm.repository.BankRepository;
import com.atm.service.AuthenticationService;
import com.atm.service.BankingService;
import com.atm.view.ATMView;

public class Main {
    public static void main(String[] args) {
        BankRepository repository = BankRepository.createWithSampleData();
        AuthenticationService authenticationService = new AuthenticationService(repository);
        BankingService bankingService = new BankingService(repository);
        ATMView view = new ATMView();

        ATMController controller = new ATMController(authenticationService, bankingService, view);
        controller.start();
    }
}
