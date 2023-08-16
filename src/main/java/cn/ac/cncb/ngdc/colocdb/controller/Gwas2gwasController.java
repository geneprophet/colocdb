package cn.ac.cncb.ngdc.colocdb.controller;

import cn.ac.cncb.ngdc.colocdb.result.Result;
import cn.ac.cncb.ngdc.colocdb.service.Gwas2gwasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Gwas2gwasController {

    @Autowired
    Gwas2gwasService gwas2gwasService;

    @CrossOrigin
    @GetMapping("/gwas2gwas")
    public Result queryGwas2gwgas(@RequestParam(value = "gwas1",required = false) String gwas1,
                             @RequestParam(value = "gwas2",required = false) String gwas2,
                             @RequestParam(value = "locus",required = false) String locus,
                             @RequestParam(value = "gene",required = false) String gene,
                             @RequestParam(value = "coloc_snp",required = false) String coloc_snp,
                             @RequestParam(value = "sort_field",required = false) String sort_field,
                             @RequestParam(value = "sort_direction",required = false) String sort_direction,
                             @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
    ){
        Result res = gwas2gwasService.queryGwas2gwas(pageSize,pageIndex-1,gwas1,gwas2,locus,gene,coloc_snp,sort_field,sort_direction);
        return res;
    }

    @CrossOrigin
    @GetMapping("/gwas2gwaslike")
    public Result queryGwas2gwgasLike(@RequestParam(value = "gwas1",required = false) String gwas1,
                                  @RequestParam(value = "gwas2",required = false) String gwas2,
                                  @RequestParam(value = "keyword",required = false) String keyword,
                                  @RequestParam(value = "locus",required = false) String locus,
                                  @RequestParam(value = "gene",required = false) String gene,
                                  @RequestParam(value = "coloc_snp",required = false) String coloc_snp,
                                      @RequestParam(value = "sort_field",required = false) String sort_field,
                                      @RequestParam(value = "sort_direction",required = false) String sort_direction,
                                  @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                  @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
    ){
        Result res = gwas2gwasService.queryGwas2gwasLike(pageSize,pageIndex-1,keyword,gwas1,gwas2,locus,gene,coloc_snp,sort_field,sort_direction);
        return res;
    }
}
