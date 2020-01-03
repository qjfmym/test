package com.example.demo1.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//分页工具类
@Data
public class PageUilt {
    private List<QuestionEntity> questions;
    private boolean showPrevious;//上一页
    private boolean showFirstPage;//第一页
    private boolean showNext;//下一页
    private boolean showEndPage;//最后一页

    private boolean page;//页码
    private List<Integer> pages=new ArrayList<>();


    public void setPage(Integer totalCount, Integer page, Integer size) {
        Integer totalPage=0;
        if (totalCount%size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }

        pages.add(page);
        for (int i=1;i<=3;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }
            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }


        //是否展示上一页
        if (page==1){
            showPrevious=false;
        }else {
            showPrevious=true;
        }
        //是否战士下一页
        if (page== totalPage){
            showNext=false;
        }else{
            showNext=true;
        }

        //是否展示第一页
        if (pages.contains(1)){
            showFirstPage=false;
        }else{
            showFirstPage=true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }
    }
}
