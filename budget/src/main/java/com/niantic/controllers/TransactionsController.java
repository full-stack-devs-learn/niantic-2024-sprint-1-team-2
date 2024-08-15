package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.Transaction;
import com.niantic.services.CategoryDao;
import com.niantic.services.TransactionDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;


@Controller
public class TransactionsController
{
    TransactionDao transactionDao = new TransactionDao();


    @GetMapping("/transactions")
    public String getTransactions(Model model) {

        ArrayList<Transaction> transactions;

        transactions = transactionDao.getAllTransactions();

        model.addAttribute("transactions", transactions);
        return "transactions/index";
    }

    @GetMapping("/transactions/add")
    public String addTransactions(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("action", "add");
        return "transactions/add_edit";
    }

    @PostMapping("/transactions/add")
    public String addActor(Model model, @ModelAttribute("transaction") Transaction transaction) {
        transactionDao.addTransaction(transaction);
        model.addAttribute("transaction", transaction);
        return "transactions/add_success";
    }






}

