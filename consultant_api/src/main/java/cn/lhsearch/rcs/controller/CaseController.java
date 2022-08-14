package cn.lhsearch.rcs.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.lhsearch.rcs.repository.*;
import cn.lhsearch.rcs.entity.*;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CaseController {

	@Autowired
	CaseRepository caseRepository;

	@GetMapping("/cases")
	public ResponseEntity<List<Case>> getAll(@RequestParam(required = false) String name) {
		try {
			List<Case> cases = new ArrayList<Case>();

			if (name == null)
				caseRepository.findAll().forEach(cases::add);
			else 
				caseRepository.findByCandidateNameContaining(name).forEach(cases::add);

			if (cases.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(cases, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/cases/{id}")
	public ResponseEntity<Case> getCaseById(@PathVariable("id") long id) {
		Optional<Case> caseData = caseRepository.findById(id);

		if (caseData.isPresent()) {
			return new ResponseEntity<>(caseData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/cases")
	public ResponseEntity<Case> createCase(@RequestBody Case case1) {
		try {
			Case _case = caseRepository
					.save(new Case(case1.getCandidateName(), case1.getAccessCode(), case1.getPublished()));
			return new ResponseEntity<>(_case, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/cases/{id}")
	public ResponseEntity<Case> updateCase(@PathVariable("id") long id, @RequestBody Case case1) {
		Optional<Case> caseData = caseRepository.findById(id);

		if (caseData.isPresent()) {
			Case _Case = caseData.get();
			_Case.setCandidateName(case1.getCandidateName());
			_Case.setAccessCode(case1.getAccessCode());
			_Case.setPublished(case1.getPublished());
			return new ResponseEntity<>(caseRepository.save(_Case), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/cases/{id}")
	public ResponseEntity<HttpStatus> deleteCase(@PathVariable("id") long id) {
		try {
			caseRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/cases")
	public ResponseEntity<HttpStatus> deleteAllCases() {
		try {
			caseRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/cases/published")
	public ResponseEntity<List<Case>> findByPublished() {
		try {
			List<Case> cases = caseRepository.findByPublished(true);

			if (cases.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cases, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
