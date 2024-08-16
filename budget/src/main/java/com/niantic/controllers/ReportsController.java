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
public class ReportsController {
    TransactionDao transactionDao = new TransactionDao();
    CategoryDao categoryDao = new CategoryDao();
    UserDao userDao = new UserDao();
    VendorDao vendorDao = new VendorDao();

    @GetMapping("/reports/category")
    public String getReportByCategory(Model model) {

        ArrayList<Category> categories = categoryDao.getAllCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());

        return "reports/category";

    }

    @GetMapping("/reports/user")
    public String getReportByUser(Model model) {

        ArrayList<User> users = userDao.getAllUsers();

        model.addAttribute("users", users);
        model.addAttribute("user", new User());

        return "reports/user";

    }

    @PostMapping("/reports/category")
    public String getReportsByCategory(Model model, @ModelAttribute("category") Category category) {
        int categoryId = category.getCategoryId();
        String categoryName = categoryDao.getCategoryById(categoryId).getCategoryName();

        ArrayList<Transaction> transactions = transactionDao.getTransactionsByCategory(categoryId);
        ArrayList<ReportLine> reportLines = new ArrayList();

        for (var transaction : transactions) {
            reportLines.add(new ReportLine(transaction));
        }
        model.addAttribute("reportlines", reportLines);
        model.addAttribute("reportType", "category");
        model.addAttribute("name", categoryName);

        return "reports/index";

    }

    @PostMapping("/reports/user")
    public String getReportsByUser(Model model, @ModelAttribute("user") User user) {
        int userId = user.getUserId();
        String userName = userDao.getUserById(userId).getUserName();

        ArrayList<Transaction> transactions = transactionDao.getTransactionsByUser(userId);
        ArrayList<ReportLine> reportLines = new ArrayList();

        for (var transaction : transactions) {
            reportLines.add(new ReportLine(transaction));
        }
        model.addAttribute("reportlines", reportLines);
        model.addAttribute("reportType", "user");
        model.addAttribute("name", userName);

        return "reports/index";

    }


}
