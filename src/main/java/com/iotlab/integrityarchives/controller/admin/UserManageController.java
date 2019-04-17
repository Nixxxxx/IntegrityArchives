/*
package com.iotlab.integrityarchives.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iotlab.integrityarchives.entity.User;
import com.iotlab.integrityarchives.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manage/user")
public class UserManageController {

	@Autowired
	private UserService userService;

	@RequestMapping("/list")
	public ModelAndView list(Integer page) {
		//page = VariateUtil.solveNullPage(page);
		List<User> userList = userService.findByPage(page, 10);
		ModelAndView mav = new ModelAndView("manage/index");
		if(!userList.isEmpty())
			mav.addObject("userList", userList);
		mav.addObject("pagePath", "manage/admin/list");
		return mav;
	}
	
	@ResponseBody
	@PostMapping("/add")
	public Map<String, Object> add(User user) {
		Map<String, Object> map = new HashMap<>();
		if(userService.create(user)) {
			map.put("success", true);
			map.put("msg", "添加成功");
		}else {
			map.put("success", false);
			map.put("msg", "添加失败");
		}
		return map;
	}
	
	@ResponseBody
	@PostMapping("/del")
	public Map<String, Object> delete(Integer id){
		Map<String, Object> map = new HashMap<>();
		if(userService.delete(id)) {
			map.put("success", true);
			map.put("msg", "删除成功");
		}else {
			map.put("success", false);
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	@ResponseBody
	@PostMapping("/update")
	public Map<String, Object> update(User user) {
		Map<String, Object> map = new HashMap<>();
		if(userService.update(user)) {
			map.put("success", true);
			map.put("msg", "更新成功");
		}else {
			map.put("success", false);
			map.put("msg", "更新失败");
		}
		return map;
	}
}
*/
