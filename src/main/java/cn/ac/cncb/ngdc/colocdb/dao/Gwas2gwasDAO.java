package cn.ac.cncb.ngdc.colocdb.dao;

import cn.ac.cncb.ngdc.colocdb.entity.Gwas2gwas;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface Gwas2gwasDAO extends PagingAndSortingRepository<Gwas2gwas,Integer>, JpaSpecificationExecutor<Gwas2gwas> {
}
