package com.yr.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yr.dao.AccountDao;
import com.yr.entity.Account;
import com.yr.mapper.AccountMapper;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao<Account> {

	@Autowired
	private AccountMapper<Account> accountMapper;

	public int login(Account account) {
		return accountMapper.login(account);
	}
	
	@Override
	public void add(Account account) {
		accountMapper.add(account);
	}

	@Override
	public void delete(int id) {
		accountMapper.delete(id);
	}

	@Override
	public void update(Account account) {
		accountMapper.update(account);
	}

	@Override
	public Account getQuery(int id) {	
		return accountMapper.getQuery(id);
	}

	@Override
	public List<Account> query() {
		return accountMapper.query();
	}

	@Override
	public void active(String name) {
		accountMapper.active(name);
	}

}
