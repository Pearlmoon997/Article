package com.example.Article.DTO;

//form 데이터를 받아 올 그릇
public class ArticleForm {
    private String title;
    private String content;

    //생성자
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //데이터 확인을 위한 toString 메소드
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
