package it.solvingteam.padelmanagement.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.solvingteam.padelmanagement.dto.NoticeDto;
import it.solvingteam.padelmanagement.dto.message.SuccessMessageDto;
import it.solvingteam.padelmanagement.dto.message.notice.InsertNoticeDto;
import it.solvingteam.padelmanagement.mapper.notice.NoticeMapper;
import it.solvingteam.padelmanagement.model.club.Club;
import it.solvingteam.padelmanagement.model.notice.Notice;
import it.solvingteam.padelmanagement.model.player.Player;
import it.solvingteam.padelmanagement.repository.NoticeRepository;

@Service
public class NoticeService {
	@Autowired
	NoticeRepository noticeRepository;

	@Autowired
	ClubService clubService;

	@Autowired
	NoticeMapper noticeMapper;
	
	@Autowired 
	PlayerService playerService;

	public NoticeDto insert(InsertNoticeDto insertNoticeDto) {
		Club club = clubService.findClubByAdmin(Long.parseLong(insertNoticeDto.getAdminId()));
		Notice notice = new Notice(insertNoticeDto.getMessage(), LocalDate.now(), club);
		notice = this.noticeRepository.save(notice);
		return noticeMapper.convertEntityToDto(notice);
	}

	public NoticeDto findById(String id) throws Exception {
		if (!StringUtils.isNumeric(id)) {
			throw new Exception("id non valido");
		}
		return noticeMapper.convertEntityToDto(this.noticeRepository.findById(Long.parseLong(id)).get());

	}

	public List<NoticeDto> findAll(String idAdmin) throws Exception {
		if (!StringUtils.isNumeric(idAdmin)) {
			throw new Exception("id non valido");
		}
		Club club = clubService.findClubByAdmin(Long.parseLong(idAdmin));
		List<NoticeDto> notice = noticeMapper.convertEntityToDto(noticeRepository.findAllNoticeByClub_id(club.getId()));
		return notice;
	}

	public NoticeDto update(NoticeDto noticeDto) throws Exception {
		Notice notice = noticeRepository.findById(Long.parseLong(noticeDto.getId())).get();
		if (notice.getId() == null) {
			throw new Exception("id non esiste");
		}
		notice.setMessage(noticeDto.getMessage());

		return noticeMapper.convertEntityToDto(noticeRepository.save(notice));
	}

	public SuccessMessageDto delete(String id) throws Exception {
		if (!StringUtils.isNumeric(id) ) {
			throw new Exception("id non valido");
		}
		Notice notice = this.noticeRepository.findById(Long.parseLong(id)).orElse(null);
		this.noticeRepository.delete(notice);
		SuccessMessageDto successDto = new SuccessMessageDto("notice eliminata con successo");
		return successDto;
	}
	
	public List<NoticeDto> findAllForPlayer(String idPlayer) throws Exception {
		if (!StringUtils.isNumeric(idPlayer)) {
			throw new Exception("id non valido");
		}
		Player player = playerService.getPlayerClub(idPlayer);
		
		List<NoticeDto> notice = noticeMapper.convertEntityToDto(noticeRepository.findAllNoticeByClub_id(player.getClub().getId()));
		return notice;
	}
}
