package com.barry.cloud.platform.web.controller.sys;

import com.barry.cloud.platform.common.parse.json.JSONMapper;
import com.barry.cloud.platform.web.entity.sys.User;
import com.barry.cloud.platform.web.model.PagerInfo;
import com.barry.cloud.platform.web.service.sys.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/10 17:50
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping(value = "/list")
//    public ModelAndView list(HttpServletRequest request){
//        String pageSizeStr = request.getParameter("pageSize");
//        String pageNoStr = request.getParameter("pageNo");
//        Integer pageSize = StringUtils.isNotBlank(pageSizeStr) ? Integer.parseInt(pageSizeStr) : 10;
//        Integer pageNo = StringUtils.isNotBlank(pageNoStr) ? Integer.parseInt(pageNoStr) : 1;
//        log.info("into user list pageSize={}, pageNo={}", pageSize, pageNo);
//        List<User> list = userService.findList(null);
//        ModelAndView view = new ModelAndView();
//        PagerInfo pager = new PagerInfo();
//        pager.setPageNo(pageNo);
//        pager.setRows(list);
//        pager.setTotalRows(100);
//        view.addObject("data", JSONMapper.writeObjectAsString(pager));
//        view.setViewName("/sys/userlist");
//        return view;
//    }


    @GetMapping(value = "/list")
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.setViewName("/sys/userlist");
        return view;
    }

    @PostMapping(value = "/list2")
    public PagerInfo list2(HttpServletRequest request) {
        String pageSizeStr = request.getParameter("pageSize");
        String pageNoStr = request.getParameter("pageNo");
        Integer pageSize = StringUtils.isNotBlank(pageSizeStr) ? Integer.parseInt(pageSizeStr) : 10;
        Integer pageNo = StringUtils.isNotBlank(pageNoStr) ? Integer.parseInt(pageNoStr) : 1;
        log.info("into user list pageSize={}, pageNo={}", pageSize, pageNo);
        List<User> list = userService.findList(null);
        PagerInfo pager = new PagerInfo();
        pager.setPageNo(pageNo);
        pager.setRows(list);
        pager.setTotalRows(100);
        return pager;
    }

    @GetMapping(value = "/add")
    public ModelAndView homepage() {
        return new ModelAndView("/sys/useradd");
    }

    @GetMapping(value = "/del")
    public void logout() {

    }


}