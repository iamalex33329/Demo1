package com.ctbcbank.lab1.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder(value = {"content", "title"})    // 調整順序
public class Article {
    @JsonIgnore
    private Long id;

    // 改變json的key輸出字串，不影響已經寫好的程式
    // @JsonProperty("auther")
    private String author;

    private String title;

    private String content;

    // 在 application.properties 已經有預設，這邊可以自定義
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    // 把值為null的key隱藏起來
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Reader> reader;
}
