import sys
from collections import deque

input = sys.stdin.readline

if __name__ == '__main__':
    N, W, L = map(int, input().split())
    trucks = list(map(int, input().split()))

    que = deque()
    que.append((1, trucks[0]))  # (트럭이 다리에 진입한 시간, 트럭의 무게)

    time = 1
    weight = trucks[0]
    idx = 1

    while que:
        time += 1

        if time - que[0][0] == W:  # 다리를 다 건넌 트럭 처리
            weight -= que.popleft()[1]

        if idx < N and weight + trucks[idx] <= L and len(que) < W:  # 새로운 트럭이 다리에 진입할 수 있을 때
            que.append((time, trucks[idx]))  # 트럭이 다리에 진입한 시간과 무게를 큐에 추가
            weight += trucks[idx]
            idx += 1

    print(time)

# 어짜피 새로운 트럭은 한 번에 하나씩 차례로 입장할 수 있음
# 트럭이 다리를 다 지나는것과 새로운 트럭이 다리에 진입하는 것은 동시에 이뤄진다 >> 트럭이 다 움직일때까지 큐는 비지 않는다

# 핵심 아이디어: 다리 위의 트럭은 '진입한 시간'으로 관리
#   * 현재시간-진입시간으로 다리를 다 건넌 시점을 알 수 있음
#   * 위 내용이 가능함으로 다리를 다 건넌 시점을 알기위해 사용하는 변수를 따로 업데이트 해주지 않아도 됨
#       - 처음엔 큐에 (다리길이, 트럭)을 넣고 시간이 지남에 따라 다리길이를 -1해주며 0일때 제거하려 했음
#       - 매 초마다 큐의 요소들을 업데이트하는 것은 비효율적