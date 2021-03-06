package com.glch.base.util;


import org.apache.poi.hssf.usermodel.*;
import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public final class ExcelHandleUtils {
    public boolean validateExcel(String filePath) {
        if ((filePath == null) || ((!isExcel2003(filePath)) && (!isExcel2007(filePath)))) {
            return false;
        }

        File file = new File(filePath);

        if ((file == null) || (!file.exists())) {
            return false;
        }

        return true;
    }

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public static void exportRecogExcel(HSSFWorkbook workbook, String[] headers, List<Map<String, Object>> resultList, OutputStream out) {
        HSSFSheet sheet = workbook.createSheet("小区表登记信息");
        sheet.setDefaultColumnWidth((short) 25);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor((short) 40);
        style.setFillPattern((short) 1);
        style.setBorderBottom((short) 1);
        style.setBorderLeft((short) 1);
        style.setBorderRight((short) 1);
        style.setBorderTop((short) 1);
        style.setAlignment((short) 2);

        HSSFFont font = workbook.createFont();
        font.setColor((short) 20);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight((short) 700);

        style.setFont(font);

        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor((short) 9);
        style2.setFillPattern((short) 1);
        style2.setBorderBottom((short) 1);
        style2.setBorderLeft((short) 1);
        style2.setBorderRight((short) 1);
        style2.setBorderTop((short) 1);
        style2.setAlignment((short) 2);
        style2.setVerticalAlignment((short) 1);

        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight((short) 400);

        style2.setFont(font2);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell((short) i);

            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
        }

        int index;
        if (resultList.size() > 0) {
            index = 1;
            for (Map<String, Object> objectMap : resultList) {
                //获取行列
                row = sheet.createRow(index);
                //获取列
                HSSFCell cell_00 = row.createCell(0);
                //定义单元格属性bai
                cell_00.setCellStyle(style2);
                HSSFRichTextString richString = new HSSFRichTextString(objectMap.get("name").toString());
                //定义名称
                cell_00.setCellValue(richString);

                HSSFCell cell_01 = row.createCell(1);
                cell_01.setCellStyle(style2);
                if (objectMap.get("gender") == null) {
                    cell_01.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("gender").toString());
                    cell_01.setCellValue(richString);
                }
                HSSFCell cell_02 = row.createCell(2);
                cell_02.setCellStyle(style2);
                if (objectMap.get("birthday") == null) {
                    cell_02.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("birthday").toString());
                    cell_02.setCellValue(richString);
                }
                HSSFCell cell_03 = row.createCell(3);
                cell_03.setCellStyle(style2);
                if (objectMap.get("addrress") == null) {
                    cell_03.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("addrress").toString());
                    cell_03.setCellValue(richString);
                }
                HSSFCell cell_04 = row.createCell(4);
                cell_04.setCellStyle(style2);
                if (objectMap.get("cardId") == null) {
                    cell_04.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("cardId").toString());
                    cell_04.setCellValue(richString);
                }
                HSSFCell cell_05 = row.createCell(5);
                cell_05.setCellStyle(style2);
                if (objectMap.get("tel") == null) {
                    cell_05.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("tel").toString());
                    cell_05.setCellValue(richString);
                }
                HSSFCell cell_06 = row.createCell(6);
                cell_06.setCellStyle(style2);
                if (objectMap.get("owner") == null) {
                    cell_06.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("owner").toString());
                    cell_06.setCellValue(richString);
                }

                HSSFCell cell_07 = row.createCell(7);
                cell_07.setCellStyle(style2);
                if (objectMap.get("ownerName") == null) {
                    cell_07.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("ownerName").toString());
                    cell_07.setCellValue(richString);
                }
                HSSFCell cell_08 = row.createCell(8);
                cell_08.setCellStyle(style2);
                if (objectMap.get("ownerCardId") == null) {
                    cell_08.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("ownerCardId").toString());
                    cell_08.setCellValue(richString);
                }
                HSSFCell cell_09 = row.createCell(9);
                cell_09.setCellStyle(style2);
                if (objectMap.get("ownerTel") == null) {
                    cell_09.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("ownerTel").toString());
                    cell_09.setCellValue(richString);
                }
                HSSFCell cell_10 = row.createCell(10);
                cell_10.setCellStyle(style2);
                if (objectMap.get("work") == null) {
                    cell_10.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("work").toString());
                    cell_10.setCellValue(richString);
                }

                HSSFCell cell_11 = row.createCell(11);
                cell_11.setCellStyle(style2);
                if (objectMap.get("estateId") == null) {
                    cell_11.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("estateId").toString());
                    cell_11.setCellValue(richString);
                }
                HSSFCell cell_12 = row.createCell(12);
                cell_12.setCellStyle(style2);
                if (objectMap.get("xqsheng") == null) {
                    cell_12.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("xqsheng").toString());
                    cell_12.setCellValue(richString);
                }

                HSSFCell cell_13 = row.createCell(13);
                cell_13.setCellStyle(style2);
                if (objectMap.get("xqshi") == null) {
                    cell_13.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("xqshi").toString());
                    cell_13.setCellValue(richString);
                }

                HSSFCell cell_14 = row.createCell(14);
                cell_14.setCellStyle(style2);
                if (objectMap.get("xqxian") == null) {
                    cell_14.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("xqxian").toString());
                    cell_14.setCellValue(richString);
                }

                HSSFCell cell_15 = row.createCell(15);
                cell_15.setCellStyle(style2);
                if (objectMap.get("xqmc") == null) {
                    cell_15.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("xqmc").toString());
                    cell_15.setCellValue(richString);
                }

                HSSFCell cell_16 = row.createCell(16);
                cell_16.setCellStyle(style2);
                if (objectMap.get("building") == null) {
                    cell_16.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("building").toString());
                    cell_16.setCellValue(richString);
                }
                HSSFCell cell_17 = row.createCell(17);
                cell_17.setCellStyle(style2);
                if (objectMap.get("unitNumber") == null) {
                    cell_17.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("unitNumber").toString());
                    cell_17.setCellValue(richString);
                }
                HSSFCell cell_18 = row.createCell(18);
                cell_18.setCellStyle(style2);
                if (objectMap.get("roomNumber") == null) {
                    cell_18.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("roomNumber").toString());
                    cell_18.setCellValue(richString);
                }
                HSSFCell cell_19 = row.createCell(19);
                cell_19.setCellStyle(style2);
                if (objectMap.get("houseType") == null) {
                    cell_19.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("houseType").toString());
                    cell_19.setCellValue(richString);
                }

                HSSFCell cell_20 = row.createCell(20);
                cell_20.setCellStyle(style2);
                if (objectMap.get("houseUse") == null) {
                    cell_20.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("houseUse").toString());
                    cell_20.setCellValue(richString);
                }

                HSSFCell cell_21 = row.createCell(21);
                cell_21.setCellStyle(style2);
                if (objectMap.get("liveType") == null) {
                    cell_21.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("liveType").toString());
                    cell_21.setCellValue(richString);
                }
                HSSFCell cell_22 = row.createCell(22);
                cell_22.setCellStyle(style2);
                if (objectMap.get("remark") == null) {
                    cell_22.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("remark").toString());
                    cell_22.setCellValue(richString);
                }

                HSSFCell cell_23 = row.createCell(23);
                cell_23.setCellStyle(style2);
                if (objectMap.get("state") == null) {
                    cell_23.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("state").toString());
                    cell_23.setCellValue(richString);
                }

                HSSFCell cell_24 = row.createCell(24);
                cell_24.setCellStyle(style2);
                if (objectMap.get("creator") == null) {
                    cell_24.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("creator").toString());
                    cell_24.setCellValue(richString);
                }
                HSSFCell cell_25 = row.createCell(25);
                cell_25.setCellStyle(style2);
                if (objectMap.get("createTime") == null) {
                    cell_25.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("createTime").toString());
                    cell_25.setCellValue(richString);
                }

                HSSFCell cell_26 = row.createCell(26);
                cell_26.setCellStyle(style2);
                if (objectMap.get("imagePath") == null) {
                    cell_26.setCellValue("");
                } else {
                    richString = new HSSFRichTextString(objectMap.get("imagePath").toString());
                    cell_26.setCellValue(richString);
                }
                index++;
            }
        }
    }
}
