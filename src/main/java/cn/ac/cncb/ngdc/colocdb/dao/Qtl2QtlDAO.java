package cn.ac.cncb.ngdc.colocdb.dao;

import cn.ac.cncb.ngdc.colocdb.entity.Qtl2Qtl;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface Qtl2QtlDAO extends PagingAndSortingRepository<Qtl2Qtl,Integer>, JpaSpecificationExecutor<Qtl2Qtl> {
}
