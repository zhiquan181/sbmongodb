package com.zhiquan.cai.sbmongodb.controller;

import com.zhiquan.cai.sbmongodb.model.ResultObject;
import com.zhiquan.cai.sbmongodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("caizhiquan")
public class UserController {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**表名*/
    private static final String collectionName = "user";

    /**
     * 描述：新增
     * @author caizhiquan
     * @created 2019/8/21 00:11
     * @param user
     * @return ResultObject
     * http://localhost:8082/caizhiquan/insert?userId=03&userName=全&passWord=kio82192&email=zhiquan181@163.com&age=11&dataStatus=1
     */
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject insert(@ModelAttribute User user) throws Exception {
        this.mongoTemplate.insert(user);
        System.out.println("--"+user+"--");
        return new ResultObject(HttpServletResponse.SC_OK);//调用ResultObject的第一个有参构造函数
    }

    /**
     * 描述：删除
     * @author caizhiquan
     * @created 2019/8/21 00:15
     * @param userId
     * @return ResultObject
     * http://localhost:8082/caizhiquan/delete?userId=015
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResultObject delete(@RequestParam("userId") String userId) throws Exception {
        Query query = Query.query(Criteria.where("userId").is(userId));
        this.mongoTemplate.remove(query, collectionName);
        return new ResultObject(HttpServletResponse.SC_OK);
    }

    /**
     * 描述：修改
     * @author caizhiquan
     * @created 2019/8/21 00:18
     * @param user
     * @return ResultObject
     * http://localhost:8082/caizhiquan/update?userId=01&userName=Chenzi&passWord=kio41&email=zhiquan181@163.com&age=11&dataStatus=2
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject update(@ModelAttribute User user) throws Exception {
        Query query = Query.query(Criteria.where("userId").is(user.getUserId()));
        Update update = new Update();
        update.set("userName",user.getUserName());
        update.set("passWord",user.getPassWord());
        update.set("email", user.getEmail());
        update.set("age", user.getAge());
        update.set("dataStatus", user.getDataStatus());
        this.mongoTemplate.updateFirst(query, update, collectionName);
        return new ResultObject(HttpServletResponse.SC_OK);
    }

    /**
     * 描述：查询
     * @author caizhiquan
     * @created 2019/8/21 00:22
     * @param
     * @return ResultObject
     * http://localhost:8082/caizhiquan/query
     */
    @RequestMapping("/query")
    @ResponseBody
    public ResultObject query() throws Exception {
        Query query = Query.query(Criteria.where(""));//查询全部
        //Query query = Query.query(Criteria.where("dataStatus").is(1));//查询单个
        List<User> users = this.mongoTemplate.find(query, User.class);
        System.out.println("--"+users+"--");
        return new ResultObject(HttpServletResponse.SC_OK, users);
    }

}