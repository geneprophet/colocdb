package cn.ac.cncb.ngdc.colocdb.service;


import cn.ac.cncb.ngdc.colocdb.dao.ColocDAO;
import cn.ac.cncb.ngdc.colocdb.entity.Coloc;
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
public class ColocService {

    @Autowired
    ColocDAO colocDAO;

    public Result queryColoc(Integer pageSize, Integer pageIndex,String trait,String qtl, String molecule, String tissue, String probe, String gene_id , String coloc_snp,String locus , String top_snp, String top_snp_gene,String sort_field,String sort_direction){
        Long total = 0L;
        List<Coloc> data = null;
        Meta meta = new Meta();

        Specification<Coloc> queryCondition = new Specification<Coloc>(){
            @Override
            public Predicate toPredicate(Root<Coloc> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (trait != null){
                    predicateList.add(criteriaBuilder.equal(root.get("trait"),trait));
                }
                if (qtl != null){
                    predicateList.add(criteriaBuilder.equal(root.get("qtl"),qtl));
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
                if (locus != null){
                    predicateList.add(criteriaBuilder.equal(root.get("locus"),locus));
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
            data = colocDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = colocDAO.count(queryCondition);
            meta.setTotal(total);
            meta.setPageIndex(pageIndex + 1);
            meta.setPageSize(pageSize);
            return ResultFactory.buildSuccessResult(data,meta);
        } catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.toString());
        }
    }
    public Result queryColoclike(Integer pageSize, Integer pageIndex,String keyword,String trait,String qtl,String molecule, String tissue, String probe, String gene_id , String coloc_snp,String locus,String top_snp,String top_snp_gene,String sort_field,String sort_direction){
        Long total = 0L;
        List<Coloc> data = null;
        Meta meta = new Meta();

        Specification<Coloc> queryCondition = new Specification<Coloc>(){
            @Override
            public Predicate toPredicate(Root<Coloc> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (keyword != null){
                    List<Predicate> predicateListOr = new ArrayList<>();
//                  只模糊匹配以keywork开头的，如果模糊匹配字段任意位置出现关键词需要两边都加上%
                    predicateListOr.add(criteriaBuilder.like(root.get("trait"),"%"+keyword+"%"));
                    predicateListOr.add(criteriaBuilder.like(root.get("qtl"),"%"+keyword+"%"));
                    predicateListOr.add(criteriaBuilder.like(root.get("coloc_snp"),"%"+keyword+"%"));
                    Predicate predicateOR = criteriaBuilder.or(predicateListOr.toArray(new Predicate[predicateListOr.size()]));
                    List<Predicate> predicateListAnd = new ArrayList<>();
                    if (trait != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("trait"),trait+"%"));
                    }
                    if (qtl != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("qtl"),qtl+"%"));
                    }
                    if (molecule != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("molecule"),molecule+"%"));
                    }
                    if (tissue != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("tissue"),tissue+"%"));
                    }
                    if (probe != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("probe"),probe+"%"));
                    }
                    if (gene_id != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("gene_id"),gene_id+"%"));
                    }
                    if (coloc_snp != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("coloc_snp"),coloc_snp+"%"));
                    }
                    if (locus != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("locus"),locus+"%"));
                    }
                    if (top_snp != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("top_snp"),top_snp+"%"));
                    }
                    if (top_snp_gene != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("top_snp_gene"),top_snp_gene+"%"));
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
                    if (trait != null){
                        predicateList.add(criteriaBuilder.like(root.get("trait"),trait+"%"));
                    }
                    if (qtl != null){
                        predicateList.add(criteriaBuilder.like(root.get("qtl"),qtl+"%"));
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
                    if (locus != null){
                        predicateList.add(criteriaBuilder.like(root.get("locus"),locus+"%"));
                    }
                    if (top_snp != null){
                        predicateList.add(criteriaBuilder.like(root.get("top_snp"),top_snp+"%"));
                    }
                    if (top_snp_gene != null){
                        predicateList.add(criteriaBuilder.like(root.get("top_snp_gene"),top_snp_gene+"%"));
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
            data = colocDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = colocDAO.count(queryCondition);
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
