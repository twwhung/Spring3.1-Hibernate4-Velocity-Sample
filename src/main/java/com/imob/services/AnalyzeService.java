package com.imob.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.imob.daoimpls.AnalyzeDaoImpl;
import com.imob.domains.PairSummary;
import com.imob.domains.SingleSummary;



@Transactional
@Service("analyzeService")
public class AnalyzeService {
	@Value("#{analyzeDaoImp1}")
	private AnalyzeDaoImpl analyzeDaoImpl;
	public List<PairSummary> listPairSummarys(int gid){
		return analyzeDaoImpl.listPairSummary(gid);
	}
	public List<SingleSummary> listSingleSummarys(int gid){
		return analyzeDaoImpl.listSingleSummary(gid);
	}
}
