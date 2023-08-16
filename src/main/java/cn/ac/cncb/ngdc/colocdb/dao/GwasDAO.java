package cn.ac.cncb.ngdc.colocdb.dao;

import cn.ac.cncb.ngdc.colocdb.entity.Gwas;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GwasDAO extends PagingAndSortingRepository<Gwas,Integer>, JpaSpecificationExecutor<Gwas> {
}
