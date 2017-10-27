import java.io.File;

/**
 * Created by zhanghengqiang on 2017/10/27.
 */
public class Test {

    @org.junit.Test
    public void test(){
        File directory = new File("D:\\logs");

        for (File file : directory.listFiles()){
            if (file.isFile()){
                System.out.println(file.getAbsolutePath().replaceAll("\\\\","|"));
            }
        }


    }


}
