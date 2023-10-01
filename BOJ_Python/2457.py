import sys

input = sys.stdin.readline

# 15:00 - 16:30 (한시간 혼자)

# 날짜 형태로 만들어서 정렬한다
# 조건, 3.1 ~ 11.30까지 무조건 꽃 하나씩 피기

if __name__ == '__main__':
    N = int(input())

    flowers = []
    for _ in range(N):
        m1, d1, m2, d2 = map(int, input().split())
        flowers.append((m1 * 100 + d1, m2 * 100 + d2))

    flowers.sort()
    cur, cnt = 301, 0
    nxt = cur
    while cur < 1201:
        for f in flowers:   # 꽃 리스트 돌면서 가장 긴 조건을 충족하는 nxt고르기
            if f[0]<=cur and nxt<f[1]:
                nxt = f[1]

        if nxt==cur:    # for문 한바퀴 돌고나왔는데 초기값인 cur==nxt==301이면 방법없음
            print(0)
            exit(0)

        cnt += 1        # if문 아닐 경우 꽃 심은것임으로 cnt++, cur은 nxt로 업데이트(마지막 날짜가 이제 현재 꽃이 펴야하는 날짜)
        cur = nxt

    print(cnt)


# # 초기코드
# # 조건에 맞으면 큐에 넣기
# # 초반엔 for문 한번만 돌면서 조건에 맞게 start를 조절해주면서 진행하려했는데, end조건이 없어서 충분해도 계속 넣음 >> 최소가 안됨
# ans = []
# tmp = deque()
# start, end = 301, 1130
# for day in lst:
#     if day[0] <= start < day[1]:
#         if tmp:
#             tmp.popleft()
#         tmp.append(day)
#         continue
#     ans += tmp
#     start = ans[-1][1]
#     tmp.clear()
#     if day[0] <= start < day[1]:
#         tmp.append(day)
