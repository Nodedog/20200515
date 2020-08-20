import java.util.HashMap;
import java.util.Map;
/*
*
*                        Map Set的面试题
*
*
* */


/*
* 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
* 找出那个只出现了一次的元素。
* */


public class TestDemo {

    public int singleNumber(int[] nums){
        //1.统计每个数字出现的次数
        //key表示具体的哪个数字(第一个Integer)，value表示出现了几次(第二个Integer)
        Map<Integer,Integer> map = new HashMap<>();
        for (int x : nums) {
            Integer count = map.get(x);
            //这里if条件中count不能等于0 因为map.get（x）返回的不是int
            if (count == null){
                //x在之前如果没有出现过，就把新的键值对插入到map中
                map.put(x,1);
            }else {
                map.put(x,count + 1);
            }
        }
        //System.out.println(map);
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if (entry.getValue().equals(1)){
                return entry.getKey();
            }
        }
        return 0;
    }



    public int singleNumber2(int[] nums) {
        /*按位异或 ^
        对位相同得0 相异的1
        性质1： a^b^b = a
        性质2： a^0 = a*/
        //int ret = 0 把数组中的每个元素 都按位异或上ret，结果仍然保存到ret 中
        //最终数组遍历完毕，ret的值就是那个出现一次的数字
    int ret = 0;
        for (int x : nums) {
            ret ^= x;
        }
        return ret;
    }


    //给定一个非空整数数组，除了两个元素只出现一次以外，其余每个元素均出现两次。
    //找出那个只出现了一次的两个元素。
    public int[] singleNumber3(int[] nums) {
        //1.先针对所有数字进行^操作
        int ret = 0;
        for (int x : nums) {
            ret ^= x;
        }
        //2.在ret中找一个补位0的bit位
        int bit = 0;
        for (;bit < 32; bit++) {
            if ((ret & (1 <<bit)) > 0){
                break;
            }
        }
        //此时bit对应的位就是为了1
        //a 和b就是最终要找的结果
        int a = 0;
        int b = 0;

        //3.根据bit对应的位分组进行^运算
        for (int x : nums) {
            if ((x & (1 << bit)) > 0){
                a ^= x;
            }else {
                b ^= x;
            }
        }
        return new int[]{a,b};
    }
33.14

    public static void main(String[] args) {
        TestDemo testDemo = new TestDemo();
        int[] array = {1,1,2,3,3,4,4};
        int ret = testDemo.singleNumber2(array);
        System.out.println(ret);
    }

}
