package com.cloud.plt.controller;

import com.cloud.common.util.base.Result;
import com.cloud.plt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/mongodbController")
public class MongodbController {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**表名*/
    private static final String collectionName = "zhengbang";

    /**
     * 描述：新增
     * @author maochengyuan
     * @created 2018/9/1 20:17
     * @param user
     * @return ResultObject
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result insert(@ModelAttribute User user) throws Exception {
        this.mongoTemplate.insert(user);
        return Result.success(HttpServletResponse.SC_OK);
    }

    /**
     * 描述：删除
     * @author maochengyuan
     * @created 2018/9/1 20:17
     * @param userId
     * @return ResultObject
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("userId") String userId) throws Exception {
        Query query = Query.query(Criteria.where("userId").is(userId));
        this.mongoTemplate.remove(query, collectionName);
        return Result.success(HttpServletResponse.SC_OK);
    }

    /**
     * 描述：修改
     * @author maochengyuan
     * @created 2018/9/1 20:17
     * @param user
     * @return ResultObject
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@ModelAttribute User user) throws Exception {
        Query query = Query.query(Criteria.where("userId").is(user.getUserId()));
        Update update = new Update();
        update.set("age", user.getAge());
        update.set("name", user.getName());
        update.set("email", user.getEmail());
        this.mongoTemplate.updateFirst(query, update, collectionName);
        return Result.success(HttpServletResponse.SC_OK);
    }

    /**
     * 描述：查询
     * @author maochengyuan
     * @created 2018/9/1 20:17
     * @param
     * @return ResultObject
     */
    @RequestMapping("/query")
    @ResponseBody
    public Result query() throws Exception {
        Query query = Query.query(Criteria.where("dataStatus").is(1));
        List<User> users = this.mongoTemplate.find(query, User.class);
        return Result.success(users);
    }

}
