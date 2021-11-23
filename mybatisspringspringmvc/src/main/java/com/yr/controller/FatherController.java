package com.yr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yr.entity.Father;
import com.yr.service.FatherService;

@Controller
public class FatherController {

	@Autowired
	private FatherService<Father> fatherService;

	@RequestMapping(path = "/fatheradd",method = RequestMethod.POST)
	public void add(Father father) {
		fatherService.add(father);
	}

	@RequestMapping(path = "/fatherdelete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		fatherService.delete(id);
		return "redirect:/pages/father/list.jsp";
	}

	@ModelAttribute
	public void modelAttribute(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			model.addAttribute("father", fatherService.getQuery(id));
		}
	}

	@RequestMapping(path = "/fatherupdate")
	public void update(@ModelAttribute("father") Father father) {
		fatherService.update(father);
	}

	@ResponseBody
	@RequestMapping(path = "/fathergetupdate")
	public Father getUpdate(int id) {
		return fatherService.getQuery(id);
	}

	@ResponseBody
	@RequestMapping(path = "/fatherquery")
	public List<Father> query() {
		System.out.println(fatherService.query());
		return fatherService.query();
	}

}
