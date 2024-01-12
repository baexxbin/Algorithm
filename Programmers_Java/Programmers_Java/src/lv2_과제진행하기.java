import java.util.*;

public class lv2_과제진행하기 {
    public static void main(String[] args) {
        String[][] plans = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        solution(plans);
    }
    public static String[] solution(String[][] plans) {
        LinkedList<Homework> lst = new LinkedList<>();
        for (String[] plan : plans) {
            lst.add(new Homework(plan[0], convert(plan[1]), Integer.parseInt(plan[2])));
        }

        lst.sort((h1, h2) -> h1.start - h2.start);
        Stack<Homework> stack = new Stack<>();
        ArrayList<String> ans = new ArrayList<>();

        while (!lst.isEmpty()){
            Homework cur = lst.poll();
            if (!lst.isEmpty() && cur.start + cur.playtime > lst.get(0).start) {
                cur.playtime -= lst.get(0).start - cur.start;
                stack.push(cur);
                continue;
            }
            ans.add(cur.name);
            if (!lst.isEmpty()) {
                int time = lst.get(0).start - cur.start - cur.playtime;
                while (time > 0 && !stack.isEmpty()) {
                    Homework rest = stack.pop();
                    if (rest.playtime > time) {
                        rest.playtime -= time;
                        time = 0;
                        stack.push(rest);
                    }else{
                        time -= rest.playtime;
                        ans.add(rest.name);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            ans.add(stack.pop().name);
        }

        String[] answer = new String[plans.length];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }

        return answer;
    }

    private static int convert(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0])*60 + Integer.parseInt(split[1]);
    }
}

class Homework{
    String name;
    int start;
    int playtime;

    Homework(String name, int start, int playtime) {
        this.name = name;
        this.start = start;
        this.playtime = playtime;
    }
}


