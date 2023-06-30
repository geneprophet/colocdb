package cn.ac.cncb.ngdc.colocdb.dao;


import cn.ac.cncb.ngdc.colocdb.entity.Coloc;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ColocDAO extends PagingAndSortingRepository<Coloc,Integer>, JpaSpecificationExecutor<Coloc> {
}
