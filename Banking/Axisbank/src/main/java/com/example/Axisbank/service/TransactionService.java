package com.example.Axisbank.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Axisbank.entity.Customer;
import com.example.Axisbank.entity.Transaction;
import com.example.Axisbank.repository.CustomerRepository;
import com.example.Axisbank.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public String deposite(Transaction transaction) {

		String accountNum = transaction.getAccountNum();
		Customer customer = customerRepository.findByAccountNo(accountNum);

		if (customer != null) {

			double balance = customer.getBalance();
			balance = balance + transaction.getAmount();
			customer.setBalance(balance);
			customerRepository.save(customer);

			String date = LocalDate.now().toString();
			transaction.setDate(date);

			transactionRepository.save(transaction);
			return "Transaction completed successfully";

		}

		return "Transaction Failed...";
	}

	public String withdraw(Transaction transaction) {

		String accountNum = transaction.getAccountNum();
		Customer customer = customerRepository.findByAccountNo(accountNum);

		if (customer != null) {

			double balance = customer.getBalance();
			balance = balance - transaction.getAmount();
			customer.setBalance(balance);
			customerRepository.save(customer);

			String date = LocalDate.now().toString();
			transaction.setDate(date);

			transactionRepository.save(transaction);
			return "Transaction completed successfully";

		}

		return "Transaction Failed...";
	}

	public List<Transaction> getAll(String accountNum) {
		return transactionRepository.findAllByAccountNum(accountNum);
	}

	public String fundTransfer(List<Transaction> transactions) {

		for (Transaction transaction : transactions) {

			if (transaction.getType().equals("CREDIT")) {

				 deposite(transaction);
			}
			if (transaction.getType().equals("DEBIT")) {
				 withdraw(transaction);
			}

		}
		return "Success";
	}

}




