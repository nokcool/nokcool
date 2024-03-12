package project1;

import java.io.*;
import java.util.Scanner;

public class issue {
    public static final int find=20;//前后文字比对个数
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入原版论文的绝对路径：");
        String w1=sc.nextLine();
        System.out.println("请输入抄袭版论文的绝对路径：");
        String w2=sc.nextLine();
        System.out.println("请输入输出答案文件的绝对路径：");
        String out=sc.nextLine();
        String word1 = getString(w1);//将原版论文写入字符串
        String word2 = getString(w2);//将抄袭版论文写入字符串
        long count = getCount(word2, word1);//计算重复字数
        long result=count*100000/word2.length();
        long[] arr = getInts(result);//计算重复率，将结果存入数组
        writeResult(arr, out);//将结果写入文件中
    }

    public static void writeResult(long[] arr, String out) throws IOException {
        String output="重复率为："+ arr[3]+"."+ arr[2]+ arr[1]+"%";
        FileWriter fw=new FileWriter(out);
        fw.write(output);
        fw.close();
    }

    public static long[] getInts(long result) {
        long []arr=new long[4];
        for(int i=0;i<3;i++){
            arr[i]= result %10;
            result /=10;
        }
        arr[3]= result;
        if(arr[0]>=5){
            arr[1]++;
            if(arr[1]==10){
                arr[1]=0;
                arr[2]++;
            }
            if(arr[2]==10){
                arr[2]=0;
                arr[3]++;
            }
        }
        return arr;
    }

    public static int getCount(String word2, String word1) {
        int i = 0;
        int count = 0;
        for (int j = i; j < word2.length(); j++) {
            char a = word2.charAt(j);
            for (int k = (0<j-find?j-find:0);
                 k < (j + find < word1.length() ? j + find : word1.length()); k++) {
                char b = word1.charAt(k);
                if (a == b) {
                    count++;
                    break;
                }
            }

        }

        return count;
    }

    public static String getString(String w1) throws IOException {
        FileReader fr = new FileReader(w1);
        int ch;
        String word1 = new String();
        while ((ch = fr.read()) != -1)
            word1 += (char) ch;
        fr.close();
        return word1;
    }
}
