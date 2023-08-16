package cn.ac.cncb.ngdc.colocdb.controller;

import cn.ac.cncb.ngdc.colocdb.result.Result;
import cn.ac.cncb.ngdc.colocdb.service.Qtl2QtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Qtl2qtlController {
    @Autowired
    Qtl2QtlService qtl2QtlService;

    @CrossOrigin
    @GetMapping("/qtl2qtl")
    public Result queryQtl2qtl(@RequestParam(value = "qtl1",required = false) String qtl1,
                                  @RequestParam(value = "qtl2",required = false) String qtl2,
                                  @RequestParam(value = "locus",required = false) String locus,
                                  @RequestParam(value = "probe_qtl1",required = false) String probe_qtl1,
                                  @RequestParam(value = "probe_qtl2",required = false) String probe_qtl2,
                                  @RequestParam(value = "gene_id_qtl1",required = false) String gene_id_qtl1,
                                  @RequestParam(value = "gene_id_qtl2",required = false) String gene_id_qtl2,
                                  @RequestParam(value = "coloc_snp",required = false) String coloc_snp,
                                  @RequestParam(value = "sort_field",required = false) String sort_field,
                                  @RequestParam(value = "sort_direction",required = false) String sort_direction,
                                  @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                  @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
    ){
        Result res = qtl2QtlService.queryQtl2qtl(pageSize,pageIndex-1,qtl1,qtl2,locus,probe_qtl1,probe_qtl2,gene_id_qtl1,gene_id_qtl2,coloc_snp,sort_field,sort_direction);
        return res;
    }

    @CrossOrigin
    @GetMapping("/qtl2qtllike")
    public Result queryQtl2qtllike(@RequestParam(value = "qtl1",required = false) String qtl1,
                               @RequestParam(value = "qtl2",required = false) String qtl2,
                               @RequestParam(value = "locus",required = false) String locus,
                               @RequestParam(value = "keyword",required = false) String keyword,
                               @RequestParam(value = "probe_qtl1",required = false) String probe_qtl1,
                               @RequestParam(value = "probe_qtl2",required = false) String probe_qtl2,
                               @RequestParam(value = "gene_id_qtl1",required = false) String gene_id_qtl1,
                               @RequestParam(value = "gene_id_qtl2",required = false) String gene_id_qtl2,
                               @RequestParam(value = "coloc_snp",required = false) String coloc_snp,
                                   @RequestParam(value = "sort_field",required = false) String sort_field,
                                   @RequestParam(value = "sort_direction",required = false) String sort_direction,
                               @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
    ){
        Result res = qtl2QtlService.queryQtl2qtlLike(pageSize,pageIndex-1,keyword,qtl1,qtl2,locus,probe_qtl1,probe_qtl2,gene_id_qtl1,gene_id_qtl2,coloc_snp,sort_field,sort_direction);
        return res;
    }


}
