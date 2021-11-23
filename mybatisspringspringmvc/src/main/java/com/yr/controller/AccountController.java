package com.yr.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Account;
import com.yr.service.AccountService;
import com.yr.util.EncryptUtils;
import com.yr.util.MailTest;

@Controller
public class AccountController {

	@Autowired
	private AccountService<Account> accountService;

	@RequestMapping(path = "/accountlogin", method = RequestMethod.POST)
	public void login(Account account, String randomCode, HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		account.setPassword(EncryptUtils.encryptToMD5(account.getPassword()));

		randomCode = randomCode.toLowerCase();
		String randCode = (String) req.getSession().getAttribute("rand");
		randCode = randCode.toLowerCase();

		if (null != randomCode && randCode.equals(randomCode)) {
			if (account.getName() != "" && account.getPassword() != "") {
				Boolean getLogin = accountService.login(account);
				if (getLogin == true) {
					req.getSession().setAttribute("name", account.getName());
					req.getSession().setAttribute("password", account.getPassword());
					resp.sendRedirect("/mybatisspringspringmvc/index.jsp");
				} else {
					resp.sendRedirect("/mybatisspringspringmvc/login.jsp?a=2");
				}
			} else {
				resp.sendRedirect("/mybatisspringspringmvc/login.jsp?a=3");
			}
		} else {
			resp.sendRedirect("/mybatisspringspringmvc/login.jsp?a=1");
		}

	}

	@RequestMapping(path = "/accountadd", method = RequestMethod.POST)
	public void add(Account account, MailTest mt) {
		account.setPassword(EncryptUtils.encryptToMD5(account.getPassword()));
		accountService.add(account);
		mt.sendMail(account.getName(), account.getEmail());
	}

	@RequestMapping(path = "/accountdelete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		accountService.delete(id);
		return "redirect:/pages/account/list.jsp";
	}

	@ModelAttribute
	public void modelAttribute(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			model.addAttribute("account", accountService.getQuery(id));
		}
	}

	@RequestMapping(path = "/accountupdate")
	public void update(@ModelAttribute("account") Account account) {
		accountService.update(account);
	}

	@ResponseBody
	@RequestMapping(path = "/accountgetupdate")
	public Account getUpdate(int id) {
		return accountService.getQuery(id);
	}

	@ResponseBody
	@RequestMapping(path = "/accountquery")
	public List<Account> query() {
		System.out.println(accountService.query());
		return accountService.query();
	}
	
	@RequestMapping(path = "/active")
	public String active(String name) {
		accountService.active(name);
		return "redirect:/pages/account/list.jsp";
	}

}
