package cn.ac.cncb.ngdc.colocdb.controller;

import cn.ac.cncb.ngdc.colocdb.result.Result;
import cn.ac.cncb.ngdc.colocdb.service.CgwasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CgwasController {
    @Autowired
    CgwasService cgwasService;

    @CrossOrigin
    @GetMapping("/cgwas")
    public Result queryCgwas(@RequestParam(value = "trait",required = false) String trait,
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
        Result res = cgwasService.queryCgwas(pageSize,pageIndex-1,trait,molecule,tissue,probe,gene_id,coloc_snp,top_snp,top_snp_gene,sort_field,sort_direction);
        return res;
    }

    @CrossOrigin
    @GetMapping("/cgwaslike")
    public Result queryCgwasLike(@RequestParam(value = "trait",required = false) String trait,
                             @RequestParam(value = "molecule",required = false) String molecule,
                             @RequestParam(value = "tissue",required = false) String tissue,
                             @RequestParam(value = "probe",required = false) String probe,
                             @RequestParam(value = "gene_id",required = false) String gene_id,
                             @RequestParam(value = "coloc_snp",required = false) String coloc_snp,
                             @RequestParam(value = "top_snp",required = false) String top_snp,
                             @RequestParam(value = "top_snp_gene",required = false) String top_snp_gene,
                             @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
    ){
        Result res = cgwasService.queryCgwasLike(pageSize,pageIndex-1,trait,molecule,tissue,probe,gene_id,coloc_snp,top_snp,top_snp_gene);
        return res;
    }
}
