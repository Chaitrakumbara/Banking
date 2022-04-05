package com.example.Axisbank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Axisbank.entity.Customer;
import com.example.Axisbank.entity.FundTransfer;
import com.example.Axisbank.entity.Login;
import com.example.Axisbank.entity.Transaction;
import com.example.Axisbank.service.CustomerService;
import com.example.Axisbank.service.TransactionService;

@RestController
@CrossOrigin(origins = "http://localhost:9090/")
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/customer")
	public Customer save(@RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@PutMapping("/customer")
	public Customer update(@RequestBody Customer customer) {
		return customerService.update(customer);
	}

	@DeleteMapping("/customer/{id}")
	public void delete(@PathVariable Long id) {
		customerService.delete(id);

	}

	@GetMapping("/customer/{id}")
	public Optional<Customer> getOne(@PathVariable Long id) {
		return customerService.getOne(id);
	}

	@GetMapping("/customer")
	public List<Customer> getAll() {
		return customerService.getAll();
	}

	@PostMapping("/login")
	public Customer authenticate(@RequestBody Login login) {
		return customerService.authenticate(login);
	}

	@PostMapping("/deposite")
	public String deposite(@RequestBody Transaction transaction) {
		return transactionService.deposite(transaction);
	}

	@PostMapping("/fundTransfers")
	public String fundTransfer(@RequestBody FundTransfer fundTransfer) {
		return transactionService.fundTransfer(fundTransfer.getTransactions());
	}

	@GetMapping("/transaction/{accountNum}")
	public List<Transaction> getAllTransactions(@PathVariable String accountNum) {
		return transactionService.getAll(accountNum);
	}

	@GetMapping("/profile/{accountNum}")
	public Customer getProfile(@PathVariable String accountNum) {
		return customerService.getProfile(accountNum);
	}

}

