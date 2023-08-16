package cn.ac.cncb.ngdc.colocdb.dao;

import cn.ac.cncb.ngdc.colocdb.entity.Xqtl;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface XqtlDAO extends PagingAndSortingRepository<Xqtl,Integer>, JpaSpecificationExecutor<Xqtl> {
}
