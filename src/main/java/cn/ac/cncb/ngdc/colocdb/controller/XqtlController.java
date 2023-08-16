package cn.ac.cncb.ngdc.colocdb.controller;

import cn.ac.cncb.ngdc.colocdb.result.Result;
import cn.ac.cncb.ngdc.colocdb.service.XqtlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XqtlController {
    @Autowired
    XqtlService xqtlService;

    @CrossOrigin
    @GetMapping("/xqtl")
    public Result queryXqtl(@RequestParam(value = "tissue_celltype",required = false) String tissue_celltype,
                            @RequestParam(value = "dataset",required = false) String dataset,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "population",required = false) String population,
                            @RequestParam(value = "qtl_type",required = false) String qtl_type,
                            @RequestParam(value = "pmid",required = false) String pmid,
                            @RequestParam(value = "sort_field",required = false) String sort_field,
                            @RequestParam(value = "sort_direction",required = false) String sort_direction,
                            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
    ){
        Result res = xqtlService.queryQtl(pageSize,pageIndex-1,dataset,description,tissue_celltype,population,qtl_type,pmid,sort_field,sort_direction);
        return res;
    }

    @CrossOrigin
    @GetMapping("/xqtllike")
    public Result queryXqtlLike(@RequestParam(value = "tissue_celltype",required = false) String tissue_celltype,
                            @RequestParam(value = "keyword",required = false) String keyword,
                            @RequestParam(value = "dataset",required = false) String dataset,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "population",required = false) String population,
                            @RequestParam(value = "qtl_type",required = false) String qtl_type,
                            @RequestParam(value = "pmid",required = false) String pmid,
                            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
    ){
        Result res = xqtlService.queryQtlLike(pageSize,pageIndex-1,keyword,dataset,description,tissue_celltype,population,qtl_type,pmid);
        return res;
    }
}
