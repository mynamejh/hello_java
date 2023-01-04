package com.example.demo.emp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.emp.mapper.EmpMapper;
import com.example.demo.emp.service.EmpVO;

@Controller
@CrossOrigin(origins="*")
public class EmpController {
	
	@Autowired
	EmpMapper empMapper;
	
	@RequestMapping(value="/empList", method = RequestMethod.GET)
	public String empList(Model model) {
	model.addAttribute("empList",empMapper.getEmpList(null));
	return "empList";
	}
	
	@RequestMapping(value="/empInfoList", method = RequestMethod.GET)
	@ResponseBody
	public List<EmpVO> empInfoList(){
		return empMapper.getEmpList(null);
	}
}
