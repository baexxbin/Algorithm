package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제 설명
 * 플레이어 입장
 *   - 방 없으면 생성 (-10, +10)
 *   - 방 있으면 입 (먼저 생긴 순, 정원 찰때까지 기다림)
 * 방 정원 차면 게임 시작
 * => 방매칭해주고 최종적으로 만들어진 방 상태, 플레이어 출력
 * 출력
 * - 방 생성 순서대로
 * - 플레이어들은 이름순
 * - 방 시작, 대기 구분
 * */
public class boj_20006 {
    static int p, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Player[] players = new Player[p];
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            players[i] = new Player(level, name);
        }

        for (int i = 0; i < p; i++) {
            ArrayList<Player> room = new ArrayList<>();
            if (!players[i].join) {
                int level = players[i].level;
                for (int j = i; j < p; j++) {
                    if (room.size()==m) break;
                    if (!players[j].join){
                        if (level - 10 <= players[j].level && players[j].level <= level + 10) {
                            players[j].join = true;
                            room.add(new Player(players[j].level, players[j].name));
                        }
                    }
                }

                Collections.sort(room);
                if (room.size()==m) sb.append("Started!").append('\n');
                else sb.append("Waiting!").append('\n');
                for (Player player : room) {
                    sb.append(player.level).append(" ").append(player.name).append("\n");
                }
            }
        }

        System.out.println(sb);
    }

    static class Player implements Comparable<Player> {
        int level;
        String name;
        boolean join = false;

        Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return this.name.compareTo(o.name);
        }
    }
}
