import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        number[] numbers = new number[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = new number(i + 1, Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(numbers, new Comparator<number>() {
            @Override
            public int compare(number o1, number o2) {
                return o1.value - o2.value;
            }
        });

        int minValue = numbers[0].value;

        int ans = -1;
        int secondMin = 0;
        for (int i = 0; i < n; i++) {
            if(minValue < numbers[i].value){
                ans = numbers[i].index;
                if(i < n-1 && numbers[i+1].value == numbers[i].value){
                    ans = -1;
                }
                break;
            }
        }


        System.out.println(ans);

    }

    public static class number {
        int index;
        int value;

        number(int index,int value) {
            this.index = index;
            this.value = value;
        }
    }
}