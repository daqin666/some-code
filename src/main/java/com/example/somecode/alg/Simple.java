package com.example.somecode.alg;

import java.util.*;

public class Simple {

    public static void main(String[] args) {
        Simple wb = new Simple();
        wb.test27();
    }

    /**
     * java.lang.Math;
     * java.util.Map;
     * java.util.List;
     * java.util.*;
     *
     * Scanner scanner = new Scanner(System.in);
     * while (scanner.hasNext()){
     *     int i = scanner.nextInt();
     *     long l = scanner.nextLong();
     *     double v = scanner.nextDouble();
     *     String s = scanner.nextLine();
     * }
     *
     * TreeSet 自动排序，由小到大
     * linkedHashSet 有序
     *
     * Character.isUpperCase()
     * Character.isDigit()
     * Character.isLetter()
     *
     * 正则：
     * 数字： "[0-9]+" / "\\d+"
     * 字母： "[A-Za-z]+"
     *
     * replace不能使用正则表达式，而replaceAll可以使用正则表达式
     *
     * String.format("%.1f", average)
     *
     */

    /**
     * 有一个整数数组，求相邻的两个数中第一个数和第二个数的相差不大于八的最大长度
     * 输入数字，第一行为数组的大小，第二行为数组的值。求其中相邻两个数字相差不大于8的最大片段的长度。
     */
    public void test(){
        int[] arr = new int[]{1, 2, 4, 6, 12, 2, 8, 6, 4};
        int maxLength = 1;
        int tempLen = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (Math.abs(arr[i] - arr[i + 1]) <= 8) {
                tempLen++;
            } else {
                maxLength = Math.max(maxLength, tempLen);
                tempLen = 1;
            }
        }
        maxLength = Math.max(maxLength, tempLen);
        System.out.println(maxLength);
    }

    /**
     * 在一个字符串中找出最小回文子串
     */
    public void test1() {
        List<String> result = new ArrayList<>();
        String str = "abccbdee";
        int length = str.length();
        if (length < 2) {
            result.add(str);
            System.out.println(result);
            return;
        }

        int minLen = length;
        List<Integer> minIndex = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (j - i + 1 <= minLen && validTest1(chars, i, j)) {
                    int tempLen = j - i + 1;
                    if(tempLen < minLen){
                        minIndex.clear();
                        minLen = tempLen;
                    }
                    minIndex.add(i);
                    break;
                }
            }
        }

        for (Integer index : minIndex) {
            System.out.println(str.substring(index, index + minLen));
        }
    }

    public boolean validTest1(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 进制转换
     */
    public void test2(){
        // 转换为10进制
        int aa = Integer.parseInt("aa", 16);
        System.out.println(aa);
    }

    /**
     * 质数因子
     * 我们判断数 num 是不是质数时，没必要从 2 一直尝试到 num 一样，此题中的大循环也大可不必写一个到 num 的循环，
     * 写到 根号 num 即可，如果此时数字还没有除数，则可判定其本身是一个质数，没有再除下去的必要了
     */
    public void test3(){
        Scanner scanner = new Scanner(System.in);

        long num = scanner.nextLong();
        long k = (long) Math.sqrt(num);

        for (long i = 2; i <= k; ++i) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
            }
        }
        System.out.println(num == 1 ? "": num+" ");
    }

    /**
     * 统计字符
     * 凡是涉及到去重统计都可以用位图实现。因为每一个不同的数据只需要用二进制的一位存储即可，大大减小了统计所使用的存储空间
     */
    public void test4(){
//        Scanner scanner = new Scanner(System.in);
//        String line = scanner.next();
        String line = "abc";
        //总共有128个字符。字需要用128位
        BitSet bitSet = new BitSet(128);
        for (char c : line.toCharArray()) {
            //判断字符c是否已出现
            if (!bitSet.get(c)) {
                //未出现就设置为已出现
                bitSet.set(c);
            }
        }
        //统计有多少字符已出现过
        System.out.println(bitSet.cardinality());
        System.out.println(bitSet.size());

        PrimitiveIterator.OfInt iterator = bitSet.stream().iterator();
        while(iterator.hasNext()){
            int i = iterator.nextInt();
            System.out.println((char)i);
        }

        /**
         * 使用nextSetBit(int fromIndex)方法从指定索引开始查找并返回第一个为true的位置。
         * 如果没有更多的true位置，则该方法会返回-1。
         */
        int index = -1;
        while ((index = bitSet.nextSetBit(index + 1)) != -1) {
            System.out.println("True position found at " + index);
        }
    }

    /**
     * 排序字符，或者排序字符串
     * Arrays.sort(array);
     */
    public void test5(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextLine();
        }
        Arrays.sort(array);
        for (String str : array) {
            System.out.println(str);
        }
    }

    /**
     * 求int型正整数在内存中存储时1的个数
     * 方法一
     * 通过31次无符号右移，逐位与1进行与运算，结果为1则计数
     * 输入： 5 输出： 2
     * <<表示左移移，不分正负数，低位补0；
     * >>表示右移，如果该数为正，则高位补0，若为负数，则高位补1；
     * >>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0
     */
    public void test6() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();    //读取数字
        int n = 0;    //计数变量
        for(int i=0; i < 32; i++){
            if((num&1) == 1)    //如果末位为1则计数
                n++;
            num = num >>> 1;    //无符号右移
        }
        System.out.println(n);
    }

    /**
     * 求int型正整数在内存中存储时1的个数
     * 方法二
     * Integer.toBinaryString()
     */
    public void test6a() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int a = in.nextInt();
            String b=Integer.toBinaryString(a);
            String c=b.replaceAll("1","");
            System.out.println(b.length()-c.length());
        }
    }

    /**
     * 删除字符串中出现次数最少的字符
     * map的相关操作
     */
    public void test7(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            char[] chars = s.toCharArray();
            //统计每个字母的数量
            HashMap<Character, Integer> map = new HashMap<>();
            for (char aChar : chars) {
                map.put(aChar, (map.getOrDefault(aChar, 0) + 1));
            }
            //找到数量最少的字符数量
            Collection<Integer> values = map.values();
            Integer min = Collections.min(values);

            //用空字符串替换该字母
            for (Character character : map.keySet()) {
                if (Objects.equals(map.get(character), min)){
                    s = s.replaceAll(String.valueOf(character), "");
                }
            }
            System.out.println(s);
        }
    }

    /**
     * 对字符串中的所有单词进行倒排，非构成单词的字符均视为单词间隔符；
     * 输入： $bo*y gi!r#l 输出： l r gi y bo
     */
    public void test8() {
        // 匹配非字母的字符进行分割
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] words = str.split("[^A-Za-z]");
        StringBuilder result = new StringBuilder();

        // 逆序添加分割完的单词
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]).append(" ");
        }
    }

    /**
     * 蛇形矩阵
     */
    public void test9() {
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt();    //读入正整数n

            int[][] result = new int[n][];    //建立数组（n行）
            int t = 1;    //记录依次赋予的数组值
            for(int i=0; i < n; i++){
                result[i] = new int[n-i];    //数组第i行有n-i个元素
                for(int j=0; j < i+1; j++){    //对第i个对角线赋值
                    result[i-j][j] = t;
                    t++;
                }
            }

            //输出数组值
            for(int[] a : result){
                for(int a1 : a)
                    System.out.print(a1 + " ");
                System.out.println();
            }
        }
    }

    /**
     * 统计每个月兔子的总数
     * 动态规划:设第n个月的兔子数量为num[n],第n个月有第n-1个月已有的兔子，同时，可能会有新出生的兔子。由题目可知，
     * 每只兔子在第三个月都会生一只兔子，所以第n个月出生的兔子数量等于第n-2月时的兔子数量，即num[n]=num[n-1]+num[n-2]。
     */
    public void test10() {
        int n = 3;
        int[] num = new int[n+1];
        num[1] = 1;
        num[2] = 1;
        for(int i=3;i<=n;i++){
            num[i] = num[i-1] + num[i-2];
        }
        System.out.println(num[n]);
    }

    /**
     * 统计字符
     * 统计其中英文字符，空格字符，数字字符，其他字符的个数
     * 依次清除英文字母、空格、数字。
     */
    public void test11() {
        Scanner in=new Scanner(System.in);
        while(in.hasNext()){
            String str=in.nextLine();
            String s1=str.replaceAll("[A-Z]+|[a-z]+", "");
            System.out.println(str.length()-s1.length());
            String s2=s1.replaceAll(" ", "");
            System.out.println(s1.length()-s2.length());
            String s3=s2.replaceAll("[0-9]+", "");
            System.out.println(s2.length()-s3.length()+"\n"+s3.length());
        }
    }

    /**
     * 链表
     * 输出单向链表中倒数第k个结点
     */
    public void test12() {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int num = scan.nextInt();
            ListNode header = new ListNode(-1);
            for (int i=0; i< num;i++) {
                int value = scan.nextInt();
                ListNode node = new ListNode(value);
                node.next = header;
                header = node;
            }
            int target = scan.nextInt();
            for (int i = 0; i < target - 1; i++) {
                header = header.next;
            }
            System.out.println(header.value);
        }
    }

    class ListNode{
        int value;
        ListNode next;
        public ListNode(){

        }
        public ListNode(int value){
            this.value = value;
        }
    }

    /**
     * 杨辉三角的变形
     * 动态规划
     * 只有n为1，2时，没有出现偶数，剩下的按照2 3 2 4的规律每四行循环一次。
     */
    public void test13() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int num = in.nextInt();
            if(num == 1 || num == 2){
                System.out.println(-1);
            }
            else if(num % 4 == 1 || num % 4 == 3){
                System.out.println(2);
            }
            else if(num % 4 == 0){
                System.out.println(3);
            }
            else if(num % 4 == 2){
                System.out.println(4);
            }
        }
    }

    /**
     * 四则运算
     */
    public void test14(){
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        //将其他括号，替换成小括号
        s=s.replace("{","(");
        s=s.replace("[","(");
        s=s.replace("}",")");
        s=s.replace("]",")");
        System.out.println(slove(s));
    }

    public static int slove(String s){
        Stack<Integer> stack=new Stack<>();
        int n=s.length();
        char[] chs=s.toCharArray();
        //初始化符号为'+'
        char sign='+';
        //记录数字
        int number=0;
        for(int i=0;i<n;i++){
            char ch=chs[i];
            //当前字符是空格，跳过
            if(ch==' ')continue;
            //当前字符是数字，拼数字
            if(Character.isDigit(ch)){
                number=number*10+ch-'0';
            }
            //如果当前字符是小括号
            if(ch=='('){
                //移到小括号后一位字符
                int j=i+1;
                //统计括号的数量
                int count=1;
                while(count>0){
                    //遇到右括号，括号数-1
                    if(chs[j]==')')count--;
                    //遇到左括号，括号数+1
                    if(chs[j]=='(')count++;
                    j++;
                }
                //递归，解小括号中的表达式
                number=slove(s.substring(i+1,j-1));
                i=j-1;
            }
            //遇到符号，将数字处理后放进栈
            if(!Character.isDigit(ch) || i==n-1){
                //如果是'+',直接入栈
                if(sign=='+'){
                    stack.push(number);
                }
                //如果是'-',数字取相反数在入栈
                else if(sign=='-'){
                    stack.push(-1*number);
                }
                //如果是'*',弹出一个数字乘后放入栈
                else if(sign=='*'){
                    stack.push(stack.pop()*number);
                }
                //如果是'/',弹出一个数字/后放入栈
                else if(sign=='/'){
                    stack.push(stack.pop()/number);
                }
                //更新符号
                sign=ch;
                //刷新数字
                number=0;
            }
        }
        //栈中数字求和得到结果
        int ans=0;
        while(!stack.isEmpty()){
            ans+=stack.pop();
        }
        return ans;
    }

    /**
     * 查找组成一个偶数最接近的两个素数
     * 任意一个偶数（大于2）都可以由2个素数组成
     */
    public void test15() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = Integer.parseInt(in.next());
            int res = count(n);
            System.out.println(res);
            System.out.println(n - res);
        }
    }

    private boolean isPrime(int num) {
        for (int i = 2; i <= num/i; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public int count(int n) {
        int i = n/2, j = n - i;
        while (!isPrime(i) || !isPrime(j)) {
            i++;
            j--;
        }
        return j;
    }

    /**
     * 放苹果
     */
    public void test16() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            System.out.println(count(m, n));
        }
    }

    // 递归功能：当前持有m个苹果，有n个盘子可供存放，返回摆放方案数
    private int count(int m, int n) {
        // 递归出口：当苹果数m=0， 此时表示什么都不做，输出1种方案，再递归下去m<0，题目要求m>=0
        // 当盘子只有一个时，剩下的苹果m只能全部摆放这个盘子，递归返回1种方案，再递归下去n==0, 题目要求n>=1
        if(m == 0 || n == 1) {
            return 1;
        }
        // 当盘子数大于苹果数，一定有n-m个盘子空着，而且每个盘子都一样，因此count(m,n)==count(m,n-1)
        if(n > m) {
            return count(m, n-1);
        } else {
            // 当盘子数<=苹果数，有两种情况：
            // 1.至少有一个盘子可以不放，因此count(m, n-1)
            // 2.至少每个盘子都有一个苹果，摆放后苹果数为(m-n)，因此coutn(m-n, n)
            return count(m, n - 1) + count(m - n, n);
        }
    }

    /**
     * 百钱买百鸡问题
     */
    public void test17() {
//        5x+3y+z/3=100;
//        x+y+z=100;
//        简化得 7x+4y=100;
        int x,y,z,middle;
        for(x = 0; x<=14;x++){
            if((100 - 7*x) % 4 == 0){
                y = (100 - 7*x) / 4;
                z = 100-x-y;
                System.out.print(x +" ");
                System.out.print(y +" ");
                System.out.print(z +" ");
                System.out.println();
            }
        }
    }

    /**
     * 计算日期到天数转换
     * 闰年和平年，闰年2月29天，平年2月28天
     * 遇到整百年时要被400整除才是闰年,否则为平年;遇到非整百年时,只要被4整除就是闰年,不能被4整除为平年
     * 简单来说，闰年 (n % 4 == 0 && n % 100 != 0) || n % 400 == 0
     */
    public void test18() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int year = sc.nextInt();
            int month = sc.nextInt();
            int day = sc.nextInt();
            int[] month_day = {31,28,31,30,31,30,31,31,30,31,30,31};
            int sum = 0;
            for(int i = 0; i < month - 1; i++){
                sum += month_day[i];
            }
            sum += day;
            if(month > 2 && is_leap(year)){
                sum += 1;
            }
            System.out.println(sum);
        }
    }

    public static boolean is_leap(int n){
        return n % 4 == 0 && n % 100 != 0 || n % 400 == 0;
    }

    /**
     * 尼科彻斯定理
     *
     * 验证尼科彻斯定理，即：任何一个整数m的立方都可以写成m个连续奇数之和。
     * 例如：
     * 1^3=1
     * 2^3=3+5
     * 3^3=7+9+11
     * 4^3=13+15+17+19
     *
     * 等差数列{an}的通项公式为：an=a1+(n-1)d。
     * 前n项和公式为：Sn=n*a1+n(n-1)d/2 或 Sn=n(a1+an)/2 。注意： 以上整数。
     * 通项公式：an=am+(n－m)d
     */
    public void test19() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            long sum = (long)Math.pow(n,3);
            int a1 = (int)sum/n - (n - 1);
            StringBuilder sb = new StringBuilder(Integer.toString(a1));
            for(int i = 1; i < n; i++){
                a1 = a1 + 2;
                sb.append("+");
                sb.append(a1);
            }
            System.out.println(sb);
        }
    }

    /**
     * 表示数字
     * 字符中所有出现的数字前后加上符号“*”，其他字符保持不变
     */
    public void test20() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            //把所有的数字段提取出来，前后加上星号再放回去
            System.out.println(input.replaceAll("([0-9]+)", "*$1*"));
        }
    }

    /**
     * 字符统计
     * 一个字符串，为不同字母出现次数的降序表示。若出现次数相同，则按ASCII码的升序输出。
     * 输入： aaddccdc 输出： cda
     * 说明： 样例里，c和d出现3次，a出现2次，但c的ASCII码比d小，所以先输出c，再输出d，最后输出a.
     *
     * 将字符串转换成字符数组。创建字符ascll码对应的整型数组，该数组长度必须大于128，字符的ascll码值就是该数组的下标，
     * 遍历字符数组，字符每出现一次对应ascll下标的整数就加一。
     */
    public void test21() {
        String str = "aaddccdc";
        char[] strArray = str.toCharArray();
        int[] chArray = new int[129];
        //字符对应ascll码值下标元素自增来统计数量
        for (char i : strArray)
            chArray[(int) i]++;
        int max = 0;
        //找出字符数量最多的ascll码值
        for (int i = 0; i < chArray.length; i++)
            if (max < chArray[i])
                max = chArray[i];
        StringBuilder sb = new StringBuilder();
        //按数量从大到小添加到可变字符序列sb
        while (max != 0) {
            for (int i = 0; i < chArray.length; i++)
                if (chArray[i] == max)
                    sb.append((char) i);
            max--;
        }
        System.out.println(sb);
    }

    /**
     * 求最小公倍数
     * 要清楚公倍数的概念就是同时可以整除两个数。所以只要一个数逐渐累加自身到可以整除另一个数时
     * 就是既可以整除自己也可以整除另一个数，此时结果就是要得到的公倍数
     */
    public void test22() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = a;                     //存储a的原始值，递归过程中使用。
        System.out.println(gcb(a,b,c));
    }
    public int gcb(int a,int b,int c){
        if (a%b== 0){                    //a累加过程中永远可以整除自身，所以可以整除b时就是最小公倍数！
            return a;
        }
        return gcb(a+c,b,c);            //a累加自身原始值，例如a=4。  a=4,8,12,16....
    }

    /**
     * 输入n，输出长度为n的由‘0’和‘1’组成的所有种类的字符串中包含10子串的个数
     */
    public void test23() {
        int n = 3;
        calculate2(n);
//        calculate3(n);
    }

    public void calculate2(int n) {
        long count = 0;
        if(n == 1) {
            count = 1;
        } else {
            count = (long) Math.pow(2, n-2) * (n-1);
        }
        System.out.println(count);
    }

    public void calculate3(int n) {
        long count = 0;
        if(n == 1) {
            count = 1;
        } else {
            count = (long) Math.pow(2, n-2) * (n-1); // *10*
            count = count + (long) Math.pow(2, n-2); // *11
        }
        System.out.println(count);
    }

    /**
     * 判断奇偶
     */
    public void test24() {
        int num = 2;
//        if ((num & 1) == 0) {
//            return true; // 偶数
//        } else {
//            return false; // 奇数
//        }

//        return num % 2 == 0 ? true : false; // 偶数为true，奇数为false
//
//        return (num ^ 1) == num + 1; // 偶数为true，奇数为false
    }

    /**
     * 输入一串字符串，输出其中大小字母及个数，没有的输出0
     */
    public void test25() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String str = in.next();
            int[] charResult = new int[128];
            char[] chars = str.toCharArray();
            for (char aChar : chars) {
                if (aChar >= 65 && aChar <= 90) {
                    charResult[aChar] = charResult[aChar] + 1;
                }
            }
            for (int i = 65; i <= 90; i++) {
                System.out.println((char) i + ":" + charResult[i]);
            }
        }
    }

    /**
     * 1)对于一个字符串str，找出字符串中第一个重复的字符，并返回该字符所在位置idx，如果没有则返回-1；
     * （例如： abcdb 第一个重复字符是b，对应idx为4；abcccb，第一个重复字符是c，对应idx为3）
     *
     * 2)现有一个字符串类型的ArrayList对象strs，基于第一问实现的方法，将strs按照每个字符串str所对应
     * 返回的idx进行重新排序（按照idx升序）
     */
    public void test26() {
        List<String> list = new ArrayList<>();
        list.add("abcdb");
        list.add("abcccb");
        list.sort((o1, o2) -> findIndex(o1) - findIndex(o2));
        System.out.println(list);
    }

    public int findIndex(String str) {
        List<Character> list = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!list.contains(chars[i])) {
                list.add(chars[i]);
            } else {
//                System.out.println(chars[i]);
//                System.out.println("idx:" + i);
                return i;
            }
        }
        return -1;
    }

    /**
     * 奖牌排名
     * 根据金银铜牌，依次比较，金牌最大的排前面，想同则比较银牌，再相同比较铜牌
     * 如果都相同，则按国家字符串的字典序排序，最终输出国家名称排名
     * 如下，第一行表示国家数量，第二行开始，第一个字符串表示国家，后续分别为金银铜牌的个数
     * 3
     * China 32 22 12
     * England 31 22 11
     * France 31 22 12
     */
    public void test27() {
        Scanner scanner = new Scanner(System.in);
        Queue<String[]> queue = new PriorityQueue<>(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                for (int i = 1; i < o1.length; i++) {
                    if (!o1[i].equals(o2[i])) {
//                        return o2[i].compareTo(o1[i]); // 存在一定问题，数字比较还是转int比较好
                        return Integer.parseInt(o2[i]) - Integer.parseInt(o1[i]);
                    }
                }
                return o1[0].compareTo(o2[0]);
            }
        });

        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            queue.offer(str.split(" "));
        }

        // 存在问题，在 PriorityQueue 中，是以数组形式保存的最大堆或者最小堆的数据
        // 所以这里foreach输出顺序并不是堆的实际顺序，得注意，输出堆数据还是得使用堆方法一个一个弹出
        queue.forEach(x -> System.out.println(x[0]));

        System.out.println();

        while (!queue.isEmpty()) {
            System.out.println(queue.poll()[0]);
        }

    }
}
