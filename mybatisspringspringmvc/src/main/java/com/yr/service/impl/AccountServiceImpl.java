package com.yr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yr.dao.AccountDao;
import com.yr.entity.Account;
import com.yr.service.AccountService;

@Service("/accountService")
public class AccountServiceImpl implements AccountService<Account> {

	@Autowired
	private AccountDao<Account> accountDao;

	public Boolean login(Account account) {
		if (accountDao.login(account) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public void add(Account account) {
		accountDao.add(account);
	}

	@Override
	public void delete(int id) {
		accountDao.delete(id);
	}

	@Override
	public void update(Account account) {
		accountDao.update(account);
	}

	@Override
	public Account getQuery(int id) {
		return accountDao.getQuery(id);
	}

	@Transactional
	@Override
	public List<Account> query() {
		return accountDao.query();
	}

	@Override
	public void active(String name) {
		accountDao.active(name);
	}

}
