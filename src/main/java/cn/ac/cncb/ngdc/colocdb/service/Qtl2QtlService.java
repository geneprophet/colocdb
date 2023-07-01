package cn.ac.cncb.ngdc.colocdb.service;

import cn.ac.cncb.ngdc.colocdb.dao.Qtl2QtlDAO;
import cn.ac.cncb.ngdc.colocdb.entity.Gwas2gwas;
import cn.ac.cncb.ngdc.colocdb.entity.Qtl2Qtl;
import cn.ac.cncb.ngdc.colocdb.result.Meta;
import cn.ac.cncb.ngdc.colocdb.result.Result;
import cn.ac.cncb.ngdc.colocdb.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class Qtl2QtlService {
    @Autowired
    Qtl2QtlDAO qtl2QtlDAO;

    public Result queryQtl2qtl(Integer pageSize, Integer pageIndex, String qtl1, String qtl2, String locus,  String probe_qtl1 ,String probe_qtl2 ,String gene_id_qtl1 , String gene_id_qtl2 ,String coloc_snp, String sort_field, String sort_direction){
        Long total = 0L;
        List<Qtl2Qtl> data = null;
        Meta meta = new Meta();

        Specification<Qtl2Qtl> queryCondition = new Specification<Qtl2Qtl>(){
            @Override
            public Predicate toPredicate(Root<Qtl2Qtl> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (qtl1 != null){
                    predicateList.add(criteriaBuilder.equal(root.get("qtl1"),qtl1));
                }
                if (qtl2 != null){
                    predicateList.add(criteriaBuilder.equal(root.get("qtl2"),qtl2));
                }
                if (locus != null){
                    predicateList.add(criteriaBuilder.equal(root.get("locus"),locus));
                }
                if (probe_qtl1 != null){
                    predicateList.add(criteriaBuilder.equal(root.get("probe_qtl1"),probe_qtl1));
                }
                if (probe_qtl2 != null){
                    predicateList.add(criteriaBuilder.equal(root.get("probe_qtl2"),probe_qtl2));
                }
                if (gene_id_qtl1 != null){
                    predicateList.add(criteriaBuilder.equal(root.get("gene_id_qtl1"),gene_id_qtl1));
                }
                if (gene_id_qtl2 != null){
                    predicateList.add(criteriaBuilder.equal(root.get("gene_id_qtl2"),gene_id_qtl2));
                }
                if (coloc_snp != null){
                    predicateList.add(criteriaBuilder.equal(root.get("coloc_snp"),coloc_snp));
                }
                if (sort_direction != null){
                    if (sort_direction.equals("ascend")){
                        criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));
                        criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sort_field)));
                        return criteriaQuery.getRestriction();
                    }else {
                        criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));
                        criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sort_field)));
                        return criteriaQuery.getRestriction();
                    }
                }else {
                    criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));
                }
                return criteriaQuery.getRestriction();
            };
        };
        try {
            data = qtl2QtlDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = qtl2QtlDAO.count(queryCondition);
            meta.setTotal(total);
            meta.setPageIndex(pageIndex + 1);
            meta.setPageSize(pageSize);
            return ResultFactory.buildSuccessResult(data,meta);
        } catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.toString());
        }
    }

    public Result queryQtl2qtlLike(Integer pageSize, Integer pageIndex, String qtl1, String qtl2, String locus,  String probe_qtl1 ,String probe_qtl2 ,String gene_id_qtl1 , String gene_id_qtl2 ,String coloc_snp){
        Long total = 0L;
        List<Qtl2Qtl> data = null;
        Meta meta = new Meta();

        Specification<Qtl2Qtl> queryCondition = new Specification<Qtl2Qtl>(){
            @Override
            public Predicate toPredicate(Root<Qtl2Qtl> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (qtl1 != null){
                    predicateList.add(criteriaBuilder.like(root.get("qtl1"),qtl1+"%"));
                }
                if (qtl2 != null){
                    predicateList.add(criteriaBuilder.like(root.get("qtl2"),qtl2+"%"));
                }
                if (locus != null){
                    predicateList.add(criteriaBuilder.like(root.get("locus"),locus+"%"));
                }
                if (probe_qtl1 != null){
                    predicateList.add(criteriaBuilder.like(root.get("probe_qtl1"),probe_qtl1+"%"));
                }
                if (probe_qtl2 != null){
                    predicateList.add(criteriaBuilder.like(root.get("probe_qtl2"),probe_qtl2+"%"));
                }
                if (gene_id_qtl1 != null){
                    predicateList.add(criteriaBuilder.like(root.get("gene_id_qtl1"),gene_id_qtl1+"%"));
                }
                if (gene_id_qtl2 != null){
                    predicateList.add(criteriaBuilder.like(root.get("gene_id_qtl2"),gene_id_qtl2+"%"));
                }
                if (coloc_snp != null){
                    predicateList.add(criteriaBuilder.like(root.get("coloc_snp"),coloc_snp+"%"));
                }
                criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));

                return criteriaQuery.getRestriction();
            };
        };
        try {
            data = qtl2QtlDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = qtl2QtlDAO.count(queryCondition);
            meta.setTotal(total);
            meta.setPageIndex(pageIndex + 1);
            meta.setPageSize(pageSize);
            return ResultFactory.buildSuccessResult(data,meta);
        } catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.toString());
        }
    }

}
