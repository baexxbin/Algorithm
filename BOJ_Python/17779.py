import sys

input = sys.stdin.readline


def divideArea(x, y, d1, d2):       # 특정 선거구의 번호로 바꾸고 인구를 세는 것이 아닌, 바로 선거구로 접근하며 인구 수 구하기
    sections = [0]*5

    # 1번 선거구
    tmp_c = y
    for r in range(x+d1):
        if r >= x:
            tmp_c -=1
        sections[0] += sum(people[r][0:tmp_c+1])

    # 3번 선거구
    tmp_c = y-d1-1
    for r in range(x+d1, N):
        sections[2] += sum(people[r][0:tmp_c+1])
        if r < x + d1 + d2:
            tmp_c += 1

    # 2번 선거구
    tmp_c = y+1
    for r in range(x+d2+1):
        if r > x:
            tmp_c += 1
        sections[1] += sum(people[r][tmp_c:])

    # 4번 선거구
    tmp_c = y + d2
    for r in range(x+d2+1, N):
        sections[3] += sum(people[r][tmp_c:])
        if r <= x + d1 + d2:
            tmp_c -= 1

    # 5번 선거구
    sections[4] = whole - sum(sections)
    return max(sections) - min(sections)



if __name__ == '__main__':

    N = int(input())
    people = [list(map(int, input().split())) for _ in range(N)]

    # 구역5 인원을 구하기 위한 전체 인구 수 구하기
    whole = sum(map(sum, people))
    ans = float('inf')

    # 가능한 (x,y) 구하기
    for x in range(N-2):
        for y in range(1, N-1):
            for d1 in range(1, y+1):
                for d2 in range(1, N-y+1):
                    if x + d1 + d2 < N and 0 <= y - d1 and y + d2 < N:  # 문제에서는 1부터 시작하고 코드의 인덱스는 0부터 시작함으로 시작과 끝 범위에 -1
                        ans = min(ans, divideArea(x, y, d1, d2))

    print(ans)

# **x, y의 범위**
# 문제에서 주어진 조건, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N에서 각각 d이동
#               x는 x< N-d1-d2 (d들의 최소값1), y는 1+d1 < y < N-d2 (문제의 x,y는 1부터 시작함으로 위의 1값은 0으로 생각)

# **d1, d2의 범위**
# d1,d2 모두 1보다 크거나 같으므로 1부터 시작
# d1: 좌하향 대각선 방향으로 y까지 변함
# d2: 우하향 대각선 방향으로 N-y까지 변함
