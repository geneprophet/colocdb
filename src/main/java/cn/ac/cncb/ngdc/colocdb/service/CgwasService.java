package cn.ac.cncb.ngdc.colocdb.service;

import cn.ac.cncb.ngdc.colocdb.dao.CgwasDAO;
import cn.ac.cncb.ngdc.colocdb.entity.Cgwas;
import cn.ac.cncb.ngdc.colocdb.entity.Coloc;
import cn.ac.cncb.ngdc.colocdb.entity.Gwas2gwas;
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
public class CgwasService {
    @Autowired
    CgwasDAO cgwasDAO;

    public Result queryCgwas(Integer pageSize, Integer pageIndex, String trait, String molecule, String tissue, String probe, String gene_id , String coloc_snp, String top_snp, String top_snp_gene, String sort_field, String sort_direction){
        Long total = 0L;
        List<Cgwas> data = null;
        Meta meta = new Meta();

        Specification<Cgwas> queryCondition = new Specification<Cgwas>(){
            @Override
            public Predicate toPredicate(Root<Cgwas> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (trait != null){
                    predicateList.add(criteriaBuilder.equal(root.get("trait"),trait));
                }
                if (molecule != null){
                    predicateList.add(criteriaBuilder.equal(root.get("molecule"),molecule));
                }
                if (tissue != null){
                    predicateList.add(criteriaBuilder.equal(root.get("tissue"),tissue));
                }
                if (probe != null){
                    predicateList.add(criteriaBuilder.equal(root.get("probe"),probe));
                }
                if (gene_id != null){
                    predicateList.add(criteriaBuilder.equal(root.get("gene_id"),gene_id));
                }
                if (coloc_snp != null){
                    predicateList.add(criteriaBuilder.equal(root.get("coloc_snp"),coloc_snp));
                }
                if (top_snp != null){
                    predicateList.add(criteriaBuilder.equal(root.get("top_snp"),top_snp));
                }
                if (top_snp_gene != null){
                    predicateList.add(criteriaBuilder.equal(root.get("top_snp_gene"),top_snp_gene));
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
            data = cgwasDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = cgwasDAO.count(queryCondition);
            meta.setTotal(total);
            meta.setPageIndex(pageIndex + 1);
            meta.setPageSize(pageSize);
            return ResultFactory.buildSuccessResult(data,meta);
        } catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.toString());
        }
    }

    public Result queryCgwasLike(Integer pageSize, Integer pageIndex,String trait,String molecule, String tissue, String probe, String gene_id , String coloc_snp,String top_snp,String top_snp_gene){
        Long total = 0L;
        List<Cgwas> data = null;
        Meta meta = new Meta();

        Specification<Cgwas> queryCondition = new Specification<Cgwas>(){
            @Override
            public Predicate toPredicate(Root<Cgwas> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (trait != null){
                    predicateList.add(criteriaBuilder.like(root.get("trait"),trait+"%"));
                }
                if (molecule != null){
                    predicateList.add(criteriaBuilder.like(root.get("molecule"),molecule+"%"));
                }
                if (tissue != null){
                    predicateList.add(criteriaBuilder.like(root.get("tissue"),tissue+"%"));
                }
                if (probe != null){
                    predicateList.add(criteriaBuilder.like(root.get("probe"),probe+"%"));
                }
                if (gene_id != null){
                    predicateList.add(criteriaBuilder.like(root.get("gene_id"),gene_id+"%"));
                }
                if (coloc_snp != null){
                    predicateList.add(criteriaBuilder.like(root.get("coloc_snp"),coloc_snp+"%"));
                }
                if (top_snp != null){
                    predicateList.add(criteriaBuilder.like(root.get("top_snp"),top_snp+"%"));
                }
                if (top_snp_gene != null){
                    predicateList.add(criteriaBuilder.like(root.get("top_snp_gene"),top_snp_gene+"%"));
                }
                criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));

                return criteriaQuery.getRestriction();
            };
        };
        try {
            data = cgwasDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = cgwasDAO.count(queryCondition);
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
