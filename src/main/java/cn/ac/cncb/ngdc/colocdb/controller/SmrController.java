package cn.ac.cncb.ngdc.colocdb.controller;

import cn.ac.cncb.ngdc.colocdb.result.Result;
import cn.ac.cncb.ngdc.colocdb.service.SmrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmrController {
    @Autowired
    SmrService smrService;

    @CrossOrigin
    @GetMapping("/smr")
    public Result querySmr(@RequestParam(value = "trait",required = false) String trait,
                             @RequestParam(value = "tissue",required = false) String tissue,
                             @RequestParam(value = "gene",required = false) String gene,
                             @RequestParam(value = "topsnp",required = false) String topsnp,
                             @RequestParam(value = "sort_field",required = false) String sort_field,
                             @RequestParam(value = "sort_direction",required = false) String sort_direction,
                             @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
    ){
        Result res = smrService.querySmr(pageSize,pageIndex-1,trait,tissue,gene,topsnp,sort_field,sort_direction);
        return res;
    }

    @CrossOrigin
    @GetMapping("/smrlike")
    public Result querySmrLike(@RequestParam(value = "trait",required = false) String trait,
                             @RequestParam(value = "keyword",required = false) String keyword,
                             @RequestParam(value = "tissue",required = false) String tissue,
                             @RequestParam(value = "gene",required = false) String gene,
                             @RequestParam(value = "topsnp",required = false) String topsnp,
                             @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex
    ){
        Result res = smrService.querySmrlike(pageSize,pageIndex-1,keyword,trait,tissue,gene,topsnp);
        return res;
    }
}
