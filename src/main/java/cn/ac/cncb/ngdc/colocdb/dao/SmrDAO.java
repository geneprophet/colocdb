package cn.ac.cncb.ngdc.colocdb.dao;

import cn.ac.cncb.ngdc.colocdb.entity.Smr;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SmrDAO extends PagingAndSortingRepository<Smr,Integer>, JpaSpecificationExecutor<Smr> {
}
