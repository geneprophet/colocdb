package cn.ac.cncb.ngdc.colocdb.dao;

import cn.ac.cncb.ngdc.colocdb.entity.Cgwas;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CgwasDAO extends PagingAndSortingRepository<Cgwas,Integer>, JpaSpecificationExecutor<Cgwas> {
}
