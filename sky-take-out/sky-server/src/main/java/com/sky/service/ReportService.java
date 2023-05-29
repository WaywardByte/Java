package com.sky.service;

import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ReportService {
    //营业额统计
    TurnoverReportVO getTurnover(LocalDate begin, LocalDate end);

    //用户统计
    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);

    //订单统计
    OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end);

    //根据时间区间统计指定状态的订单数量
    Integer getOrderCount(LocalDateTime beginTime, LocalDateTime endTime, Integer completed);

    //销量排名
    SalesTop10ReportVO getSalesTop10(LocalDate begin, LocalDate end);

    //导出30天运营表
    void exportBusinessData(HttpServletResponse response);
}
