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

import com.yr.entity.People;
import com.yr.service.PeopleService;

@Controller
public class PeopleController {

	@Autowired
	private PeopleService<People> peopleService;

	@RequestMapping(path = "/peopleadd", method = RequestMethod.POST)
	public void add(People people) {
		peopleService.add(people);
	}

	@RequestMapping(path = "/peopledelete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		peopleService.delete(id);
		return "redirect:/pages/people/list.jsp";
	}

	@ModelAttribute
	public void modelAttribute(@RequestParam(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			model.addAttribute("people", peopleService.getQuery(id));
		}
	}

	@RequestMapping(path = "/peopleupdate")
	public void update(@ModelAttribute("people") People people) {
		peopleService.update(people);
	}

	@ResponseBody
	@RequestMapping(path = "/peoplegetupdate")
	public People getUpdate(int id) {
		return peopleService.getQuery(id);
	}

	@ResponseBody
	@RequestMapping(path = "/peoplequery")
	public List<People> query() {
		System.out.println(peopleService.query());
		return peopleService.query();
	}

}
