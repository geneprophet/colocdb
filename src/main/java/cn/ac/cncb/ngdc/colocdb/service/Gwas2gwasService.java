package cn.ac.cncb.ngdc.colocdb.service;

import cn.ac.cncb.ngdc.colocdb.dao.Gwas2gwasDAO;
import cn.ac.cncb.ngdc.colocdb.entity.Coloc;
import cn.ac.cncb.ngdc.colocdb.entity.Gwas2gwas;
import cn.ac.cncb.ngdc.colocdb.entity.Xqtl;
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
public class Gwas2gwasService {
    @Autowired
    Gwas2gwasDAO gwas2gwasDAO;

    public Result queryGwas2gwas(Integer pageSize, Integer pageIndex, String gwas1, String gwas2, String locus, String gene , String coloc_snp, String sort_field, String sort_direction){
        Long total = 0L;
        List<Gwas2gwas> data = null;
        Meta meta = new Meta();

        Specification<Gwas2gwas> queryCondition = new Specification<Gwas2gwas>(){
            @Override
            public Predicate toPredicate(Root<Gwas2gwas> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (gwas1 != null){
                    predicateList.add(criteriaBuilder.equal(root.get("gwas1"),gwas1));
                }
                if (gwas2 != null){
                    predicateList.add(criteriaBuilder.equal(root.get("gwas2"),gwas2));
                }
                if (locus != null){
                    predicateList.add(criteriaBuilder.equal(root.get("locus"),locus));
                }
                if (gene != null){
                    predicateList.add(criteriaBuilder.equal(root.get("gene"),gene));
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
            data = gwas2gwasDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = gwas2gwasDAO.count(queryCondition);
            meta.setTotal(total);
            meta.setPageIndex(pageIndex + 1);
            meta.setPageSize(pageSize);
            return ResultFactory.buildSuccessResult(data,meta);
        } catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.toString());
        }
    }

    public Result queryGwas2gwasLike(Integer pageSize, Integer pageIndex,String keyword, String gwas1, String gwas2, String locus, String gene , String coloc_snp,String sort_field, String sort_direction){
        Long total = 0L;
        List<Gwas2gwas> data = null;
        Meta meta = new Meta();

        Specification<Gwas2gwas> queryCondition = new Specification<Gwas2gwas>(){
            @Override
            public Predicate toPredicate(Root<Gwas2gwas> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (keyword != null){
                    List<Predicate> predicateListOr = new ArrayList<>();
//                  只模糊匹配以keywork开头的，如果模糊匹配字段任意位置出现关键词需要两边都加上%
                    predicateListOr.add(criteriaBuilder.like(root.get("gwas1"),"%"+keyword+"%"));
                    predicateListOr.add(criteriaBuilder.like(root.get("gwas2"),"%"+keyword+"%"));
                    predicateListOr.add(criteriaBuilder.like(root.get("locus"),"%"+keyword+"%"));
                    predicateListOr.add(criteriaBuilder.like(root.get("gene"),"%"+keyword+"%"));
                    predicateListOr.add(criteriaBuilder.like(root.get("coloc_snp"),"%"+keyword+"%"));
                    Predicate predicateOR = criteriaBuilder.or(predicateListOr.toArray(new Predicate[predicateListOr.size()]));
                    List<Predicate> predicateListAnd = new ArrayList<>();
                    if (gwas1 != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("gwas1"),gwas1+"%"));
                    }
                    if (gwas2 != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("gwas2"),gwas2+"%"));
                    }
                    if (locus != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("locus"),locus+"%"));
                    }
                    if (gene != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("gene"),gene+"%"));
                    }
                    if (coloc_snp != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("coloc_snp"),coloc_snp+"%"));
                    }
                    Predicate predicateAND = criteriaBuilder.and(predicateListAnd.toArray(new Predicate[predicateListAnd.size()]));
                    if (sort_direction != null){
                        if (sort_direction.equals("ascend")){
                            criteriaQuery.where(predicateOR,predicateAND);
                            criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sort_field)));
                            return criteriaQuery.getRestriction();
                        }else {
                            criteriaQuery.where(predicateOR,predicateAND);
                            criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sort_field)));
                            return criteriaQuery.getRestriction();
                        }
                    }else {
                        criteriaQuery.where(predicateOR,predicateAND);
                        return criteriaQuery.getRestriction();
                    }
                } else {
                    List<Predicate> predicateList = new ArrayList<>();
                    if (gwas1 != null){
                        predicateList.add(criteriaBuilder.like(root.get("gwas1"),gwas1+"%"));
                    }
                    if (gwas2 != null){
                        predicateList.add(criteriaBuilder.like(root.get("gwas2"),gwas2+"%"));
                    }
                    if (locus != null){
                        predicateList.add(criteriaBuilder.like(root.get("locus"),locus+"%"));
                    }
                    if (gene != null){
                        predicateList.add(criteriaBuilder.like(root.get("gene"),gene+"%"));
                    }
                    if (coloc_snp != null){
                        predicateList.add(criteriaBuilder.like(root.get("coloc_snp"),coloc_snp+"%"));
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
                        return criteriaQuery.getRestriction();
                    }
                }

            };
        };
        try {
            data = gwas2gwasDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = gwas2gwasDAO.count(queryCondition);
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
