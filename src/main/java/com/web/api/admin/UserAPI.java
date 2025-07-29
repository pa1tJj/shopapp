package com.web.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.model.dto.UserDTO;
import com.web.service.UserService;

@RestController
@RequestMapping("/admin/user")
public class UserAPI {

	@Autowired
	private UserService userService;
	
	@PostMapping("/edit")
	public ResponseEntity<UserDTO> addOrUpdateUser(@ModelAttribute UserDTO userDTO) {
		return ResponseEntity.ok(userService.addOrUpdateUser(userDTO));
	}
	
	@DeleteMapping("/{ids}")
	public ResponseEntity<Void> deleteUser(@PathVariable List<Long> ids) {
		userService.deleteUser(ids);
		return ResponseEntity.noContent().build();
	}
}
