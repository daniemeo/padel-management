package it.solvingteam.padelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.notice.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{
	public List<Notice> findAllNoticeByClub_id(Long id);
}
