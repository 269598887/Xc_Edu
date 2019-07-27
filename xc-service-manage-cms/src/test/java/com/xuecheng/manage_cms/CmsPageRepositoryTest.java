package com.xuecheng.manage_cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {
    @Autowired
    CmsPageRepository cmsPageRepository;
    //查询所有
    @Test
    public void testFindAll(){

        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);
    }
    //分页查询
    @Test
    public void testFindPage(){
        int page=0;//从0开始
        int size=10;//每页记录数
        Pageable pageable=new PageRequest(page,size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all);
    }

    //添加
    @Test
    public void testInsert(){
        CmsPage cmsPage=new CmsPage();
        cmsPage.setSiteId("s02");
        cmsPage.setTemplateId("t02");
        cmsPage.setPageName("测试页面01");
        cmsPage.setPageCreateTime(new Date());
        List<CmsPageParam> cmsPageParams=new ArrayList<>();
        CmsPageParam cmsPageParam=new CmsPageParam();
        cmsPageParam.setPageParamName("param1");
        cmsPageParam.setPageParamValue("value1");
        cmsPageParams.add(cmsPageParam);
        cmsPage.setPageParams(cmsPageParams);
        cmsPageRepository.save(cmsPage);
        System.out.println(cmsPage);
    }


    //删除
    @Test
    public void testDelete(){
       cmsPageRepository.deleteById("5d3929acca8db3350cf4225a");
    }
    //修改
    @Test
    public void testUpdate(){
        //查询对象
        /*
        optional是JDK1.8引入的类型,optional是一个容器对象,它包括了我们需要的对象
        使用isPresent方法判断所包含的对象是否为空
        特点:
        提醒你非空判断
        将对象非空检测标准化
         */
        Optional<CmsPage> optional = cmsPageRepository.findById("5d392950ca8db33e8c732f54");
        if(optional.isPresent()){
            CmsPage cmsPage=optional.get();
            cmsPage.setPageName("测试页面888");
            cmsPageRepository.save(cmsPage);
        }
        //设置要修改的值
        //修改
    }

    //自定义dao接口
    //根据页面名称查询
    public void testfindByPageName(){
        CmsPage cmsPage = cmsPageRepository.findByPageName("测试页面888");
        System.out.println(cmsPage);
    }

    @Test
    public void findAllByExample(){
        int page=0;//从0开始
        int size=10;//每页记录数
        Pageable pageable=new PageRequest(page,size);
        //条件值对象
        CmsPage cmsPage=new CmsPage();
        //要查询5af942190e661827d8e2f5e3站点的页面
       // cmsPage.setSiteId("5af942190e661827d8e2f5e3");
        //设置模板id条件
       // cmsPage.setTemplateId("5aec5dd70e661808240ab7a6");
        //设置页面别名
        cmsPage.setPageAliase("轮播");
        //条件匹配器
       /* ExampleMatcher exampleMatcher=ExampleMatcher.matching();
        exampleMatcher=exampleMatcher.withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());*/
        ExampleMatcher exampleMatcher=ExampleMatcher.matching().
                withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        //定义Example
        Example<CmsPage> example=Example.of(cmsPage,exampleMatcher);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        List<CmsPage> content = all.getContent();
        System.out.println(content);
    }
}
