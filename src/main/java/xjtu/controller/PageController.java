package xjtu.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;
import xjtu.bean.Picture;
import xjtu.dao.PageRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    private PageRespository pageRespository;

    public PageController(PageRespository pageRespository) {
        this.pageRespository = pageRespository;
    }

    @RequestMapping(value = "/page", method=RequestMethod.GET)
    @ResponseBody
    public Page<Picture> getEntryByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "16") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return pageRespository.findAll(pageable);
    }
}
