package cn.ac.cncb.ngdc.colocdb.service;

import cn.ac.cncb.ngdc.colocdb.dao.GwasDAO;
import cn.ac.cncb.ngdc.colocdb.entity.Gwas;
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
public class GwasService {
    @Autowired
    GwasDAO gwasDAO;

    public Result queryGwas(Integer pageSize, Integer pageIndex, String datasetid, String description, String trait, String population, String trait_type,String pmid, String sort_field, String sort_direction){
        Long total = 0L;
        List<Gwas> data = null;
        Meta meta = new Meta();

        Specification<Gwas> queryCondition = new Specification<Gwas>(){
            @Override
            public Predicate toPredicate(Root<Gwas> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (datasetid != null){
                    predicateList.add(criteriaBuilder.equal(root.get("datasetid"),datasetid));
                }
                if (description != null){
                    predicateList.add(criteriaBuilder.equal(root.get("description"),description));
                }
                if (trait != null){
                    predicateList.add(criteriaBuilder.equal(root.get("trait"),trait));
                }
                if (population != null){
                    predicateList.add(criteriaBuilder.equal(root.get("population"),population));
                }
                if (trait_type != null){
                    predicateList.add(criteriaBuilder.equal(root.get("trait_type"),trait_type));
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
            data = gwasDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = gwasDAO.count(queryCondition);
            meta.setTotal(total);
            meta.setPageIndex(pageIndex + 1);
            meta.setPageSize(pageSize);
            return ResultFactory.buildSuccessResult(data,meta);
        } catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.toString());
        }
    }

    public Result queryGwasLike(Integer pageSize, Integer pageIndex,String keyword,String datasetid, String description,String trait,String population, String trait_type,String pmid){
        Long total = 0L;
        List<Gwas> data = null;
        Meta meta = new Meta();

        Specification<Gwas> queryCondition = new Specification<Gwas>(){
            @Override
            public Predicate toPredicate(Root<Gwas> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (keyword != null){
                    List<Predicate> predicateListOr = new ArrayList<>();
//                  只模糊匹配以keywork开头的，如果模糊匹配字段任意位置出现关键词需要两边都加上%
                    predicateListOr.add(criteriaBuilder.like(root.get("trait"),"%"+keyword+"%"));
                    predicateListOr.add(criteriaBuilder.like(root.get("description"),"%"+keyword+"%"));
                    Predicate predicateOR = criteriaBuilder.or(predicateListOr.toArray(new Predicate[predicateListOr.size()]));
                    List<Predicate> predicateListAnd = new ArrayList<>();
                    if (trait != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("trait"),trait+"%"));
                    }
                    if (description != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("description"),description+"%"));
                    }
                    if (datasetid != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("datasetid"),datasetid+"%"));
                    }
                    if (population != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("population"),population+"%"));
                    }
                    if (trait_type != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("trait_type"),trait_type+"%"));
                    }
                    if (pmid != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("pmid"),pmid+"%"));
                    }
                    Predicate predicateAND = criteriaBuilder.and(predicateListAnd.toArray(new Predicate[predicateListAnd.size()]));
                    criteriaQuery.where(predicateOR,predicateAND);
                    return criteriaQuery.getRestriction();
                } else {
                    List<Predicate> predicateList = new ArrayList<>();
                    if (trait != null){
                        predicateList.add(criteriaBuilder.like(root.get("trait"),trait+"%"));
                    }
                    if (description != null){
                        predicateList.add(criteriaBuilder.like(root.get("description"),description+"%"));
                    }
                    if (datasetid != null){
                        predicateList.add(criteriaBuilder.like(root.get("datasetid"),datasetid+"%"));
                    }
                    if (population != null){
                        predicateList.add(criteriaBuilder.like(root.get("population"),population+"%"));
                    }
                    if (trait_type != null){
                        predicateList.add(criteriaBuilder.like(root.get("trait_type"),trait_type+"%"));
                    }
                    if (pmid != null){
                        predicateList.add(criteriaBuilder.like(root.get("pmid"),pmid+"%"));
                    }
                    return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
                }

            };
        };
        try {
            data = gwasDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = gwasDAO.count(queryCondition);
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
