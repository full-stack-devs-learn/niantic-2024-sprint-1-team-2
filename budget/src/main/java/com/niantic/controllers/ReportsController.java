package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.ReportLine;
import com.niantic.models.Transaction;
import com.niantic.models.User;
import com.niantic.services.CategoryDao;
import com.niantic.services.TransactionDao;
import com.niantic.services.UserDao;
import com.niantic.services.VendorDao;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class ReportsController
{
    TransactionDao transactionDao = new TransactionDao();
    CategoryDao categoryDao = new CategoryDao();
    UserDao userDao = new UserDao();
    VendorDao vendorDao = new VendorDao();

    @GetMapping("/reports/category")
    public String getReportByCategory(Model model)
    {

        ArrayList<Category> categories = categoryDao.getAllCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());

        return "reports/category";

    }

    @PostMapping("/reports/category")
    public String getReportsByCategory(Model model, @ModelAttribute("category") Category category)
    {
        int categoryId = category.getCategoryId();
        ArrayList<Transaction> transactions =  transactionDao.getTransactionsByCategory(categoryId);
        ArrayList<ReportLine> reportLines = new ArrayList();
//        model.addAttribute("transactions", transactions);

        for(var transaction : transactions)
        {
            // getting user id from transactions to get username for report line
            String userName = userDao.getUserById(transaction.getUserId()).getUserName();
            String vendorName = vendorDao.getVendorById(transaction.getVendorId()).getVendorName();
            String categoryName = categoryDao.getCategoryById(transaction.getCategoryId()).getCategoryName();

            reportLines.add(new ReportLine(userName, categoryName, vendorName, transaction.getDate(), transaction.getAmount(), transaction.getNotes()));
        }
        model.addAttribute("reportlines", reportLines);

        return "reports/index";

    }


}
