package cn.ac.cncb.ngdc.colocdb.service;

import cn.ac.cncb.ngdc.colocdb.dao.SmrDAO;
import cn.ac.cncb.ngdc.colocdb.entity.Coloc;
import cn.ac.cncb.ngdc.colocdb.entity.Smr;
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
public class SmrService {
    @Autowired
    SmrDAO smrDAO;

    public Result querySmr(Integer pageSize, Integer pageIndex, String trait,String trait_description,String qtl,String tissue,String qtl_type, String gene, String topsnp, String sort_field, String sort_direction){
        Long total = 0L;
        List<Smr> data = null;
        Meta meta = new Meta();

        Specification<Smr> queryCondition = new Specification<Smr>(){
            @Override
            public Predicate toPredicate(Root<Smr> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (trait != null){
                    predicateList.add(criteriaBuilder.equal(root.get("trait"),trait));
                }
                if (trait_description != null){
                    predicateList.add(criteriaBuilder.equal(root.get("trait_description"),trait_description));
                }
                if (qtl != null){
                    predicateList.add(criteriaBuilder.equal(root.get("qtl"),qtl));
                }
                if (tissue != null){
                    predicateList.add(criteriaBuilder.equal(root.get("tissue"),tissue));
                }
                if (qtl_type != null){
                    predicateList.add(criteriaBuilder.equal(root.get("qtl_type"),qtl_type));
                }
                if (gene!= null){
                    predicateList.add(criteriaBuilder.equal(root.get("gene"),gene));
                }
                if (topsnp != null){
                    predicateList.add(criteriaBuilder.equal(root.get("topsnp"),topsnp));
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
            data = smrDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = smrDAO.count(queryCondition);
            meta.setTotal(total);
            meta.setPageIndex(pageIndex + 1);
            meta.setPageSize(pageSize);
            return ResultFactory.buildSuccessResult(data,meta);
        } catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.toString());
        }
    }

    public Result querySmrlike(Integer pageSize, Integer pageIndex,String keyword, String trait,String trait_description,String qtl,String tissue,String qtl_type, String gene, String topsnp, String sort_field, String sort_direction){
        Long total = 0L;
        List<Smr> data = null;
        Meta meta = new Meta();

        Specification<Smr> queryCondition = new Specification<Smr>(){
            @Override
            public Predicate toPredicate(Root<Smr> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (keyword != null){
                    List<Predicate> predicateListOr = new ArrayList<>();
//                  只模糊匹配以keywork开头的，如果模糊匹配字段任意位置出现关键词需要两边都加上%
                    predicateListOr.add(criteriaBuilder.like(root.get("trait"),keyword+"%"));
                    predicateListOr.add(criteriaBuilder.like(root.get("qtl"),keyword+"%"));
//                    predicateListOr.add(criteriaBuilder.like(root.get("topsnp"),"%"+keyword+"%"));
                    Predicate predicateOR = criteriaBuilder.or(predicateListOr.toArray(new Predicate[predicateListOr.size()]));
                    List<Predicate> predicateListAnd = new ArrayList<>();
                    if (trait != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("trait"),trait+"%"));
                    }
                    if (trait_description != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("trait_description"),trait_description+"%"));
                    }
                    if (qtl != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("qtl"),qtl+"%"));
                    }
                    if (tissue != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("tissue"),tissue+"%"));
                    }
                    if (gene != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("gene"),gene+"%"));
                    }
                    if (topsnp != null){
                        predicateListAnd.add(criteriaBuilder.like(root.get("topsnp"),topsnp+"%"));
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
                    if (trait_description != null){
                        predicateList.add(criteriaBuilder.like(root.get("trait_description"),trait_description+"%"));
                    }
                    if (qtl != null){
                        predicateList.add(criteriaBuilder.like(root.get("qtl"),qtl+"%"));
                    }
                    if (tissue != null){
                        predicateList.add(criteriaBuilder.like(root.get("tissue"),tissue+"%"));
                    }
                    if (qtl_type != null){
                        predicateList.add(criteriaBuilder.like(root.get("qtl_type"),qtl_type+"%"));
                    }
                    if (gene != null){
                        predicateList.add(criteriaBuilder.like(root.get("gene"),gene+"%"));
                    }
                    if (topsnp != null){
                        predicateList.add(criteriaBuilder.like(root.get("topsnp"),topsnp+"%"));
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
            data = smrDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize)).getContent();
            total = smrDAO.count(queryCondition);
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
