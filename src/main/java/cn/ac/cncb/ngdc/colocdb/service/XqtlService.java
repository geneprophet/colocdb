package cn.ac.cncb.ngdc.colocdb.service;

import cn.ac.cncb.ngdc.colocdb.dao.XqtlDAO;
import cn.ac.cncb.ngdc.colocdb.entity.Gwas;
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
public class XqtlService {
    @Autowired
    XqtlDAO xqtlDAO;

    public Result queryQtl(Integer pageSize, Integer pageIndex, String dataset, String description, String tissue_celltype, String population, String qtl_type, String pmid, String sort_field, String sort_direction){
        Long total = 0L;
        List<Xqtl> data = null;
        Meta meta = new Meta();

        Specification<Xqtl> queryCondition = new Specification<Xqtl>(){
            @Override
            public Predicate toPredicate(Root<Xqtl> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (dataset != null){
                    predicateList.add(criteriaBuilder.equal(root.get("dataset"),dataset));
                }
                if (description != null){
                    predicateList.add(criteriaBuilder.equal(root.get("description"),description));
                }
                if (tissue_celltype != null){
                    predicateList.add(criteriaBuilder.equal(root.get("tissue_celltype"),tissue_celltype));
                }
                if (population != null){
                    predicateList.add(criteriaBuilder.equal(root.get("population"),population));
                }
                if (qtl_type != null){
                    predicateList.add(criteriaBuilder.equal(root.get("qtl_type"),qtl_type));
                }
                if (pmid != null){
                    predicateList.add(criteriaBuilder.equal(root.get("pmid"),pmid));
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
            data = xqtlDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = xqtlDAO.count(queryCondition);
            meta.setTotal(total);
            meta.setPageIndex(pageIndex + 1);
            meta.setPageSize(pageSize);
            return ResultFactory.buildSuccessResult(data,meta);
        } catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.toString());
        }
    }
    public Result queryQtlLike(Integer pageSize, Integer pageIndex,String keyword, String dataset, String description, String tissue_celltype, String population, String qtl_type, String pmid){
        Long total = 0L;
        List<Xqtl> data = null;
        Meta meta = new Meta();

        Specification<Xqtl> queryCondition = new Specification<Xqtl>(){
            @Override
            public Predicate toPredicate(Root<Xqtl> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (keyword != null){
                    List<Predicate> predicateListOr = new ArrayList<>();
//                  只模糊匹配以keywork开头的，如果模糊匹配字段任意位置出现关键词需要两边都加上%
                    predicateListOr.add(criteriaBuilder.like(root.get("trait"),"%"+keyword+"%"));
                    predicateListOr.add(criteriaBuilder.like(root.get("description"),"%"+keyword+"%"));
                    Predicate predicateOR = criteriaBuilder.or(predicateListOr.toArray(new Predicate[predicateListOr.size()]));
                    List<Predicate> predicateListAnd = new ArrayList<>();
                    if (dataset != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("dataset"),dataset+"%"));
                    }
                    if (description != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("description"),description+"%"));
                    }
                    if (qtl_type != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("qtl_type"),qtl_type+"%"));
                    }
                    if (population != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("population"),population+"%"));
                    }
                    if (tissue_celltype != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("tissue_celltype"),tissue_celltype+"%"));
                    }
                    if (pmid != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("pmid"),pmid+"%"));
                    }
                    Predicate predicateAND = criteriaBuilder.and(predicateListAnd.toArray(new Predicate[predicateListAnd.size()]));
                    criteriaQuery.where(predicateOR,predicateAND);
                    return criteriaQuery.getRestriction();
                } else {
                    List<Predicate> predicateList = new ArrayList<>();
                    if (dataset != null){
                        predicateList.add(criteriaBuilder.like(root.get("dataset"),dataset+"%"));
                    }
                    if (description != null){
                        predicateList.add(criteriaBuilder.like(root.get("description"),description+"%"));
                    }
                    if (tissue_celltype != null){
                        predicateList.add(criteriaBuilder.like(root.get("tissue_celltype"),tissue_celltype+"%"));
                    }
                    if (population != null){
                        predicateList.add(criteriaBuilder.like(root.get("population"),population+"%"));
                    }
                    if (qtl_type != null){
                        predicateList.add(criteriaBuilder.like(root.get("qtl_type"),qtl_type+"%"));
                    }
                    if (pmid != null){
                        predicateList.add(criteriaBuilder.like(root.get("pmid"),pmid+"%"));
                    }
                    return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
                }

            };
        };
        try {
            data = xqtlDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = xqtlDAO.count(queryCondition);
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
