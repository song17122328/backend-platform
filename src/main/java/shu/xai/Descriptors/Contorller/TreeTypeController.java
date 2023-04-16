package shu.xai.Descriptors.Contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shu.xai.Descriptors.Dao.TreeTypeDao;
import shu.xai.Descriptors.Entity.TreeType;

import java.util.List;

@RestController
@RequestMapping("/TreeType")
public class TreeTypeController {

    //   自动装配
    @Autowired
    private TreeTypeDao treeTypeDao;

    @GetMapping
    public List<TreeType> GetAll(){
        return treeTypeDao.findAll();
    }

}
