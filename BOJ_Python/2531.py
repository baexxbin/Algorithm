import sys
from collections import defaultdict

input = sys.stdin.readline


if __name__ == '__main__':
    N, d, k, c = map(int, input().split())
    belt = [int(input()) for _ in range(N)]

    # mx = 0
    # for st in range(N):
    #     ed = (st+k)%N
    #     if st+k < N:
    #         tmp = len(set(belt[st:st+k] + [c]))
    #     else:
    #         tmp = len(set(belt[st:]+belt[:ed] + [c]))
    #     mx = max(mx, tmp)
    #
    # print(mx)

    st = 0
    ed = k
    eat = defaultdict(int)
    for i in range(st, ed):     # 초기 배열 값 초기화
        nw = belt[i]
        eat[nw] += 1
    eat[c] += 1

    ans = len(eat)
    while st < N:
        front = belt[st]
        back = belt[ed % N]

        eat[front] = eat[front]-1
        if eat[front] == 0:
            eat.pop(front)

        eat[back] += 1

        st += 1
        ed += 1
        ans = max(ans, len(eat))

    print(ans)

# 그냥 st, ed만 쓴다고 투포인터가 아님.
# 윈도우 크기만큼의 리스트의 내용을 매번 계산하는것이 아닌, 하나빼고 넣는것 계산만 추가해서 효율적으로 이용해야함
# 기존 코드: 항상 새로운 set()값 계산 O(N*k) , 3280ms
# 새로운 코드: 초기화된 코드를 바탕으로 값 업데이트 O(N) , 100ms