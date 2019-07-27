package com.xuecheng.manage_cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
}
