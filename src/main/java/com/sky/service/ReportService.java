package com.sky.service;

import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 * ClassName: ReportService
 * Package: com.sky.service
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/7/21 19:33
 * @Version 1.0
 */
public interface ReportService {
    TurnoverReportVO getTurnoverStatistics(LocalDate begin,LocalDate end);

    OrderReportVO getOrdersStatistics(LocalDate begin, LocalDate end);

    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);

    SalesTop10ReportVO getTop10(LocalDate begin, LocalDate end);

    void export(HttpServletResponse httpServletResponse);
}
