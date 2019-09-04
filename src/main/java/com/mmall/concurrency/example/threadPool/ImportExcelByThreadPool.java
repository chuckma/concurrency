package com.mmall.concurrency.example.threadPool;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 */
@Slf4j
public class ImportExcelByThreadPool {

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //处理器核心数
        int processor = Runtime.getRuntime().availableProcessors() * 2;
        //HSSFWorkbook 一个sheet页只能写入六万多条数据
        HSSFWorkbook workBook = new HSSFWorkbook();
        //创建格式
        CellStyle style = workBook.createCellStyle();
        //居中格式
        style.setAlignment(HorizontalAlignment.CENTER);
        //创建sheet页
        HSSFSheet sheet = workBook.createSheet();
        //创建一行
        HSSFRow hssfRow = sheet.createRow(0);
        HSSFCell hssfCell = hssfRow.createCell(0);
        hssfCell.setCellStyle(style);
        hssfCell.setCellValue("第" + 1 + "个sheet页，第一行，第一个单元格");

        hssfCell = hssfRow.createCell(1);
        hssfCell.setCellStyle(style);
        hssfCell.setCellValue("第" + 1 + "个sheet页，第一行，第二个单元格");

        hssfCell = hssfRow.createCell(2);
        hssfCell.setCellStyle(style);
        hssfCell.setCellValue("第" + 1 + "个sheet页，第一行，第三个单元格");

//        processor = 80;
        //手工创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(processor, processor, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(),
                new ThreadFactoryBuilder().setNameFormat("poi-task-%d").build());
        //计数器 等待线程池中的线程执行完毕
        CountDownLatch countDownLatch = new CountDownLatch(processor);

        long startTime = System.currentTimeMillis();
        Future<?> submit = null;
        for (int i = 1; i <= processor; i++) {
            int start = (i - 1) * 100 + 1;
            int end = i * 100;
            //放入线程池中
            executorService.execute(() -> createRows(sheet, start, end, countDownLatch));
//                System.out.println(submit.get().toString());
//            createRows(sheet, start, end, countDownLatch);
        }
        try {
            //等待所有线程执行完毕
            countDownLatch.await();
            //关闭线程池
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FileOutputStream fou = null;
        try {
//            fou = new FileOutputStream("E:\\threadpool\\multiSheet.xls");
            fou = new FileOutputStream("/Users/lucasma/IdeaProjects/concurrency/test.xls");
            workBook.write(fou);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fou != null) {
                try {
                    fou.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long endTime = System.currentTimeMillis();
        log.info("总计用时： {}",endTime - startTime);
    }

    private static void createRows(HSSFSheet hSSFSheet, int startRow, int endRow, CountDownLatch countDownLatch) {
        HSSFRow hssfRows;
        HSSFCell hSSFCells;
        int i = startRow;
        try {
            while (i <= endRow) {
                hssfRows = getRows(hSSFSheet, i);
                hSSFCells = hssfRows.createCell(0);
                hSSFCells.setCellValue("第" + (i + 1) + "行，第一个单元格");

                hSSFCells = hssfRows.createCell(1);
                hSSFCells.setCellValue("第" + (i + 1) + "行，第一个单元格");

                hSSFCells = hssfRows.createCell(2);
                hSSFCells.setCellValue("第" + (i + 1) + "行，第一个单元格");
                ++i;
            }
        } finally {
            countDownLatch.countDown();
        }
    }

    /**
     * 创建表格 这里要加锁
     *
     * @param hSSFSheet
     * @param row
     * @return
     */
    private static HSSFRow getRows(HSSFSheet hSSFSheet, int row) {
        lock.lock();
        try {
            return hSSFSheet.createRow(row);
        }finally {
            lock.unlock();
        }
        /*synchronized (Object.class) {
            return hSSFSheet.createRow(row);
        }*/
    }
}