package com.bank.mybank.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.mybank.constants.ApplicationConstants;
import com.bank.mybank.dto.BranchResponseDto;
import com.bank.mybank.entity.IfscDetail;
import com.bank.mybank.exception.IFSCNotFoundException;
import com.bank.mybank.repository.IfscDetailRepository;

@Service
public class BranchServiceImpl implements BranchService {
	@Autowired
	IfscDetailRepository ifscDetailRepository;

	@Override
	public Optional<BranchResponseDto> getBankDetails(String ifscCode) throws IFSCNotFoundException {

		BranchResponseDto branchResponseDto = new BranchResponseDto();
		IfscDetail ifscDetail = ifscDetailRepository.findByIfscCode(ifscCode);
		if(ifscDetail == null) {
			throw new IFSCNotFoundException(ApplicationConstants.IFSC_FAILUREMESSAGE);
		}
		BeanUtils.copyProperties(ifscDetail, branchResponseDto);
		return Optional.of(branchResponseDto);
	}

}