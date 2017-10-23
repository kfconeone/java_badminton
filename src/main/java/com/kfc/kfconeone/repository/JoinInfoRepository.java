package com.kfc.kfconeone.repository;

import com.kfc.kfconeone.models.JoinInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JoinInfoRepository extends CrudRepository<JoinInfo,Long>{
    JoinInfo findByGatherIdAndAccountId(String _gatherId,String _accountId);
    List<JoinInfo> findByAccountId(String _accountId);
    List<JoinInfo> findByGatherId(String _gatherId);
}
