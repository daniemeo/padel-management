package it.solvingteam.padelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.solvingteam.padelmanagement.model.joinProposal.JoinProposal;

public interface JoinProposalRepository extends JpaRepository<JoinProposal, Long>{
	public List<JoinProposal> findAllJoinProposalByUser_Id(Long id);
	
	@Query("from JoinProposal j join fetch j.club c where c.admin.id = ?1")
	public List<JoinProposal> findAllJoinProposalByAdmin(Long id);
	 
}
