import java.util.*;

// 모든 보석을 포함하는 가장 짧은 구간 찾기
// 투포인터? 슬라이딩윈도우
// for문 돌면서 포인터 right움직임(사야하는 보석 다사면 멈추기 check배열)
// rigth를 움직였을때 left에 해당하는 보석의 수가 0보다크면 빼기, 0이하면 유지 (딕셔너리)
public class lv3_보석쇼핑 {
    public static void main(String[] args) {
        String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        System.out.println(Arrays.toString(solution(gems)));
    }
    public static int[] solution(String[] gems) {
        Set<String> jewelTypes = new HashSet<>(Arrays.asList(gems));
        HashMap<String, Integer> jewelCounts = new HashMap<>();
        int left = 0;
        int minStart = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < gems.length; right++) {
            jewelCounts.put(gems[right], jewelCounts.getOrDefault(gems[right], 0) + 1);

            while (jewelCounts.size() == jewelTypes.size()) {
                if (right - left < minLength) {
                    minLength = right - left;
                    minStart = left;
                }

                jewelCounts.put(gems[left], jewelCounts.get(gems[left]) - 1);
                if (jewelCounts.get(gems[left]) == 0) {     // 새로운 구간을 찾기 위해 이전 구간에서 필요없는 보석 제거
                    jewelCounts.remove(gems[left]);
                }
                left++;
            }
        }

        return new int[]{minStart + 1, minStart + minLength + 1};
    }
}

// 투포인터보다 슬라이딩 윈도우
// left 하나씩 움직이는것이 아닌, 조건이 만족했을때 while문에서 left계속 이동하기
// check배열 혹은 set따로 없어도 걍 buyLst 사이즈로 판단할 수 있음;
// 최소 길이를 구해야함으로 minLength변수로 조건 설정

// 기존 코드는 조건 만족하면 바로 반복문 탈출해서 더 작은 경우 못찾음