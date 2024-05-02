import java.util.Arrays;
import java.util.function.Function;

public class Main implements Function<int[], String> {

    @Override
    public String apply(int[] nums) {
        Arrays.sort(nums);
        StringBuilder result = new StringBuilder();
        int count = 0;
        boolean containsTarget = Arrays.binarySearch(nums, 0) >= 0;

        int prev = Integer.MIN_VALUE; // Предыдущее значение, чтобы проверить на дубликаты

        for (int num : nums) {
            // Если текущее число не равно предыдущему, добавляем его в результа
            if (containsTarget){
                if (num != prev && count != 1 && num != 0) {
                    result.append(num);
                    prev = num; // Обновляем предыдущее значение
                    count ++;
                }
                else if (num != prev && count == 1 && num != 0){
                    result.append(0);
                    result.append(num);
                    prev = num; // Обновляем предыдущее значение
                    count ++;
                }
            }
            else{
                if (num != prev) {
                    result.append(num);
                    prev = num; // Обновляем предыдущее значение
                }
            }

        }

        return result.toString();
    }

    public static void main(String[] args) {
        Main minValueFunction = new Main();
        int[] nums = {1, 3, 0, 1, 8, 4, 1, 0, 1, 7, 9};
        System.out.println("minValue({" + Arrays.toString(nums) + "}) ==> return (" + minValueFunction.apply(nums) + ")");
    }
}
