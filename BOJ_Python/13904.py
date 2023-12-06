import sys
from collections import deque

input = sys.stdin.readline

# 과제
# 14:03 - 14:54

# 높은 점수 순으로 정렬 후, 해당 날짜에 과제 처리하도록
# 현재날짜가 해당 날짜보다 크면 패쓰
# 이미 해당 일에 배정된 과제가 있다면 그 전날로 배정(반복)

if __name__ == '__main__':
    N = int(input())
    work = [list(map(int, input().split())) for _ in range(N)]
    work.sort(key=lambda x: -x[1])
    mx_day = max(work, key=lambda x:x[0])[0]

    que = deque()
    que.extend(work)

    day = 0
    check = [0] * (mx_day + 1)
    while que:
        cur_d, cur_w = que.popleft()
        if cur_d - day < 0:
            continue

        tmp = cur_d
        while check[tmp] != 0 and tmp > 0:
            tmp -= 1
        if tmp <= 0:
            continue
        check[tmp] = cur_w

    print(sum(check))

# 처음 런타임 에러 (IndexError) 발생 이유
# check의 길이를 N+1로 했었는데 N은 단지 과제의 개수일 뿐, 최대 마감일자는 입력으로 주어지는 d의 최대값으로 설정해야 함!