package xjtu.controller;

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
    public Page<Picture> getEntryByPageable(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                                    Pageable pageable) {
        return pageRespository.findAll(pageable);
    }
}
