package cn.ac.cncb.ngdc.colocdb.controller;


import cn.ac.cncb.ngdc.colocdb.result.Meta;
import cn.ac.cncb.ngdc.colocdb.result.Result;
import cn.ac.cncb.ngdc.colocdb.result.ResultFactory;
import cn.ac.cncb.ngdc.colocdb.service.ColocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ColocController {
    @Autowired
    ColocService colocService;

//    @Autowired
//    ColocoverviewDAO colocoverviewDAO;
//
//    @CrossOrigin
//    @GetMapping("/colocoverview")
//    public Result queryOverview(){
//        List<Colocoverview> colocoverviewList = colocoverviewDAO.findAll();
//        return ResultFactory.buildSuccessResult(colocoverviewList,new Meta());
//    }

    @CrossOrigin
    @GetMapping("/coloc")
    public Result queryColoc(@RequestParam(value = "trait",required = false) String trait,
                             @RequestParam(value = "molecule",required = false) String molecule,
                             @RequestParam(value = "tissue",required = false) String tissue,
                             @RequestParam(value = "probe",required = false) String probe,
                             @RequestParam(value = "gene_id",required = false) String gene_id,
                             @RequestParam(value = "coloc_snp",required = false) String coloc_snp,
                             @RequestParam(value = "top_snp",required = false) String top_snp,
                             @RequestParam(value = "top_snp_gene",required = false) String top_snp_gene,
                             @RequestParam(value = "sort_field",required = false) String sort_field,
                             @RequestParam(value = "sort_direction",required = false) String sort_direction,
                             @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
                             ){
        Result res = colocService.queryColoc(pageSize,pageIndex-1,trait,molecule,tissue,probe,gene_id,coloc_snp,top_snp,top_snp_gene,sort_field,sort_direction);
        return res;
    }

    @CrossOrigin
    @GetMapping("/coloclike")
    public Result queryColoclike(@RequestParam(value = "trait",required = false) String trait,
                             @RequestParam(value = "keyword",required = false) String keyword,
                             @RequestParam(value = "molecule",required = false) String molecule,
                             @RequestParam(value = "tissue",required = false) String tissue,
                             @RequestParam(value = "probe",required = false) String probe,
                             @RequestParam(value = "gene_id",required = false) String gene_id,
                             @RequestParam(value = "coloc_snp",required = false) String coloc_snp,
                             @RequestParam(value = "top_snp",required = false) String top_snp,
                             @RequestParam(value = "top_snp_gene",required = false) String top_snp_gene,
                                 @RequestParam(value = "sort_field",required = false) String sort_field,
                                 @RequestParam(value = "sort_direction",required = false) String sort_direction,
                                 @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                 @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
    ){
        Result res = colocService.queryColoclike(pageSize,pageIndex-1,keyword,trait,molecule,tissue,probe,gene_id,coloc_snp,top_snp,top_snp_gene,sort_field,sort_direction);
        return res;
    }

}
