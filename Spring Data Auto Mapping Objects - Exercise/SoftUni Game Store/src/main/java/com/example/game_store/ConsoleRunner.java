package com.example.game_store;

import com.example.game_store.exceptions.*;
import com.example.game_store.services.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private ExecutorService executorService;

    @Autowired
    public ConsoleRunner(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        try {
            while (!input.equals("END")) {
                System.out.println(this.executorService.execute(input));
                input = sc.nextLine();
            }
        } catch (AccountNotLoggedInException | AnotherAccountInUseException | NoSuchAccountException | UserAlreadyExistsException | WrongCredentialsException |
                GameAlreadyExistsException | InsufficientPermissionException | InvalidGameException | GameNotExistsException | NoSuchOperationExists e) {
            System.out.println(e.getMessage());
        }
    }
}
