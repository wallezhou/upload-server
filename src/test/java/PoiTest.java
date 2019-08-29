import com.walle.upload.util.ExcelPoiReaderUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author ：zhouwei
 * @date ：Created in 2019/8/29 20:34
 * @description：${description}
 * @modified By：
 */
public class PoiTest {
    @Test
    public void test1() {
        String filePath = "D:\\Tmp\\test2.xlsx";
        String columns[] = {"姓名", "部门", "工号", "出勤天数"};
        List<Map<String, String>> list = new ExcelPoiReaderUtil().readExcel(filePath, columns);
        //遍历解析出来的list
        for (Map<String, String> map : list) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.print(entry.getKey() + ":" + entry.getValue() + ",");
            }
            System.out.println();
        }
    }

    public  void readExcel() throws IOException {
//        FileSystemView fsv = FileSystemView.getFileSystemView();
//        String desktop = fsv.getHomeDirectory().getPath();
        String filePath = "D:\\Tmp\\test.xlsx";

        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        HSSFSheet sheet = workbook.getSheet("Sheet1");

        int lastRowIndex = sheet.getLastRowNum();
        System.out.println(lastRowIndex);
        for (int i = 0; i <= lastRowIndex; i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) { break; }

            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                String cellValue = row.getCell(j).getStringCellValue();
                System.out.println(cellValue);
            }
        }


        bufferedInputStream.close();
    }

    @Test
    public void test02() throws IOException{
        readExcel();
    }
//    @Test
//    public void excelRead() throws Exception {
//        //创建输入流，接受目标excel文件
//        FileInputStream in = new FileInputStream(new File("D:/Temp/test.xlsx"));
//        //得到POI文件系统对象
//        POIFSFileSystem fs = new POIFSFileSystem(in);
//        //得到Excel工作簿对象
//        HSSFWorkbook wk = new HSSFWorkbook(fs);
//        //得到Excel工作簿的第一页，即excel工作表对象
//        HSSFSheet sheet = wk.getSheetAt(0);
//        //遍历工作表
//        //遍历行对象
//        for (Row row : sheet) {
//            //打印行索引
//            //System.out.println(row.getRowNum());
//            //遍历单元格对象
//            //表头不要打印
//            for (Cell cell : row) {
//                //获取每个单元格的类型
//                int cellType = cell.getCellType();
//                if(cellType == cell.CELL_TYPE_BLANK){
//                    //System.out.println("空格类型");
//                    System.out.print("\t");
//                }
//                if(cellType == cell.CELL_TYPE_NUMERIC){
//                    //System.out.println("数字类型");
//                    System.out.print(cell.getNumericCellValue()+"\t");
//                }
//                if(cellType == cell.CELL_TYPE_STRING){
//                    //System.out.println("字符串类型");
//                    System.out.print(cell.getStringCellValue()+"\t");
//                }
//            }
//            //换行
//            System.out.println();
//        }
//
//    }
}