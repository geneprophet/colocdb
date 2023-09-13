package cn.ac.cncb.ngdc.colocdb.controller;

import cn.ac.cncb.ngdc.colocdb.result.Result;
import cn.ac.cncb.ngdc.colocdb.service.GwasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GwasController {
    @Autowired
    GwasService gwasService;

    @CrossOrigin
    @GetMapping("/gwas")
    public Result queryGwas(@RequestParam(value = "trait",required = false) String trait,
                             @RequestParam(value = "datasetid",required = false) String datasetid,
                             @RequestParam(value = "dataset",required = false) String dataset,
                             @RequestParam(value = "description",required = false) String description,
                             @RequestParam(value = "population",required = false) String population,
                             @RequestParam(value = "trait_type",required = false) String trait_type,
                             @RequestParam(value = "pmid",required = false) String pmid,
                             @RequestParam(value = "sort_field",required = false) String sort_field,
                             @RequestParam(value = "sort_direction",required = false) String sort_direction,
                             @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
    ){
        Result res = gwasService.queryGwas(pageSize,pageIndex-1,datasetid,dataset,description,trait,population,trait_type,pmid,sort_field,sort_direction);
        return res;
    }

    @CrossOrigin
    @GetMapping("/gwaslike")
    public Result queryGwasLike(@RequestParam(value = "trait",required = false) String trait,
                            @RequestParam(value = "datasetid",required = false) String datasetid,
                            @RequestParam(value = "dataset",required = false) String dataset,
                            @RequestParam(value = "keyword",required = false) String keyword,
                            @RequestParam(value = "description",required = false) String description,
                            @RequestParam(value = "population",required = false) String population,
                            @RequestParam(value = "trait_type",required = false) String trait_type,
                            @RequestParam(value = "pmid",required = false) String pmid,
                            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
    ){
        Result res = gwasService.queryGwasLike(pageSize,pageIndex-1,keyword,datasetid,dataset,description,trait,population,trait_type,pmid);
        return res;
    }
}
