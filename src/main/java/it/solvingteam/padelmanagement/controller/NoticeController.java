package it.solvingteam.padelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.solvingteam.padelmanagement.dto.NoticeDto;
import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.dto.message.notice.InsertNoticeDto;
import it.solvingteam.padelmanagement.exception.BindingResultException;
import it.solvingteam.padelmanagement.model.admin.Admin;
import it.solvingteam.padelmanagement.model.player.Player;
import it.solvingteam.padelmanagement.service.AdminService;
import it.solvingteam.padelmanagement.service.NoticeService;
import it.solvingteam.padelmanagement.service.PlayerService;
import it.solvingteam.padelmanagement.util.TokenDecripter;

@RestController
@RequestMapping("dashboard")
public class NoticeController {

	@Autowired
	NoticeService noticeService;
	
	@Autowired 
	AdminService adminService;
	
	@Autowired
	PlayerService playerService;
	
	@PostMapping("insert")
	public ResponseEntity<NoticeDto> insert(@Valid @RequestBody InsertNoticeDto insertNoticeDto,
			BindingResult bindingResult) throws Exception {
		String username = TokenDecripter.decripter();
		Admin admin = adminService.findByUsername(username);
		insertNoticeDto.setAdminId(String.valueOf(admin.getId()));
		if (bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		NoticeDto noticeDto = noticeService.insert(insertNoticeDto);
		return ResponseEntity.status(HttpStatus.OK).body(noticeDto);
	}
	@GetMapping("show/{id}")
	public ResponseEntity<NoticeDto> show(@PathVariable("id") String id) throws Exception, BindingResultException {
		return ResponseEntity.status(HttpStatus.OK).body(noticeService.findById(id));

	}
	
	@GetMapping("listAllPerAdmin")
	public ResponseEntity<List<NoticeDto>> listAllForAdmin() throws Exception { 
		String username = TokenDecripter.decripter();
		Admin admin = adminService.findByUsername(username);
		List<NoticeDto> notice = noticeService.findAll(String.valueOf(admin.getId()));
		return ResponseEntity.status(HttpStatus.OK).body(notice);
	}

	@PutMapping("update")
	public ResponseEntity<NoticeDto> update(@Valid @RequestBody NoticeDto noticeDto, BindingResult bindingResult)throws Exception {
		if (bindingResult.hasErrors()) {
			throw new BindingResultException(bindingResult);
		}
		NoticeDto noticeDtoUpdate = noticeService.update(noticeDto);
		return ResponseEntity.status(HttpStatus.OK).body(noticeDtoUpdate);
	}
	
	@DeleteMapping("delete/{id}")
    public ResponseEntity<SuccessMessageDto> delete(@PathVariable String id) throws Exception{

        return ResponseEntity.status(HttpStatus.OK).body(noticeService.delete(id));
	}
	
	@GetMapping("dashboardPlayer")
	public ResponseEntity<List<NoticeDto>> listAllForPlayer() throws Exception { 
		String username = TokenDecripter.decripter();
		Player player = playerService.findByUsername(username);
		List<NoticeDto> notice = noticeService.findAllForPlayer(String.valueOf(player.getId()));
		return ResponseEntity.status(HttpStatus.OK).body(notice);
	}
}
