package com.imob.daos;

import java.util.List;

import com.imob.domains.PairSummary;
import com.imob.domains.SingleSummary;



public interface AnalyzeDao {
	public List<PairSummary> listPairSummary(final int gid);
	public List<SingleSummary> listSingleSummary(final int gid);
}
